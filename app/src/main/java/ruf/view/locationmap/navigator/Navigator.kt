package ruf.view.locationmap.navigator

import android.support.annotation.IdRes
import android.support.annotation.MainThread
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import java.util.*
import kotlin.collections.HashSet
import kotlin.reflect.KClass

@MainThread
class Navigator(@IdRes private val containerId: Int, val navigatorScopeName: Any) : INavigator {

    var counter = 0

    private var fragmentManager: FragmentManager? = null

    private val stack = Stack<Pair<FragmentModule, CustomizationCommand>>()
    private val childNavigators = HashSet<NavigatorModule>()

    override fun forwardIfEmpty(getModule: ICustomizationCommand.() -> FragmentModule) {
        if (stack.isEmpty()) forward(getModule)
    }

    override fun forward(getModule: ICustomizationCommand.() -> FragmentModule) {
        val customizationCommand = CustomizationCommand()
        getModule(customizationCommand).run {
            stack.push(this to customizationCommand)
            navigatorScopeName = this@Navigator.navigatorScopeName
            installModule()
            fragmentManager?.show(this, customizationCommand)
        }
    }

    override fun replace(getModule: ICustomizationCommand.() -> FragmentModule) {
        if (stack.isNotEmpty()) stack.popAndClose()
        forward(getModule)
    }

    override fun back(customization: ICustomizationCommand.() -> Unit): Boolean {
        val customizationCommand = CustomizationCommand()
        if (stack.isEmpty()) return false
        stack.popAndClose()
        if (stack.isEmpty()) return false
        customization(customizationCommand)
        fragmentManager?.show(stack.peek().first, customizationCommand)
        return true
    }

    override fun backTo(getModule: ICustomizationCommand.() -> KClass<out FragmentModule>): Int {
        val customizationCommand = CustomizationCommand()
        if (stack.isEmpty()) return -1
        val distance = stack.search(stack.findLast { it::class == getModule(customizationCommand)::class })
        if (distance <= 1) return 0
        for (i in 1 until distance) {
            stack.popAndClose()
        }
        fragmentManager?.show(stack.peek().first, customizationCommand)
        return distance - 1
    }

    override fun showDialog(getModule: ICustomizationCommand.() -> DialogFragmentModule) {
        val customizationCommand = CustomizationCommand()
        getModule(customizationCommand).run {
            navigatorScopeName = this@Navigator.navigatorScopeName
            installModule()
            fragmentManager?.add(this, customizationCommand)
        }
    }

    override fun startNewNavigatorOn(containerId: Int): INavigator {
        val navigatorModule = NavigatorModule(containerId)
        childNavigators.add(navigatorModule)
        navigatorModule.installModule()
        return navigatorModule.navigator
    }

    override fun attachFragmentManager(fragmentManager: FragmentManager) {
        this.fragmentManager = fragmentManager
        stack.takeIf { it.isNotEmpty() }?.peek()?.run {
            fragmentManager.show(first, second)
        }
    }

    override fun detachFragmentManager() {
        fragmentManager = null
        childNavigators.forEach {
            it.navigator.detachFragmentManager()
        }
    }

    override fun destroy() {
        stack.takeIf { it.isNotEmpty() }?.forEach {
            fragmentManager?.remove(it.first, it.second)
            it.first.close()
        }
        childNavigators.forEach { it.close() }
        stack.clear()
        fragmentManager = null
    }

    private fun Stack<Pair<FragmentModule, CustomizationCommand>>.popAndClose() = pop().first.close()

    private fun FragmentManager.show(module: FragmentModule, customization: CustomizationCommand?) {
        (module as? DialogFragmentModule)?.also { add(it, customization) }
            ?: findFragmentByTag(module.scopeName)
            ?: run {
                beginTransaction()
                    .customizeTransaction(customization)
                    .replace(containerId, module.createFragment(), module.scopeName)
                    .commitNow()
            }
    }

    private fun FragmentManager.add(module: DialogFragmentModule, customization: CustomizationCommand?) {
        findFragmentByTag(module.scopeName) ?: run {
            beginTransaction()
                .customizeTransaction(customization)
                .add(module.createFragment(), module.scopeName)
                .commitNow()
        }
    }

    private fun FragmentManager.remove(module: FragmentModule, customization: CustomizationCommand?) {
        findFragmentByTag(module.scopeName)?.also {
            beginTransaction()
                .customizeTransaction(customization)
                .remove(it)
                .commitNow()
        }
    }

    private fun FragmentTransaction.customizeTransaction(cc: CustomizationCommand?): FragmentTransaction {
        cc?.customize(this)
        return this
    }
}