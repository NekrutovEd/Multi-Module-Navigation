package ruf.view.locationmap.navigator

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import ruf.view.locationmap.sample.log
import java.util.*
import kotlin.reflect.KClass

class Navigator(
    @IdRes private val containerId: Int,
    private val navigatorScopeName: String/*,
    savedInstanceState: Bundle?*/
) : INavigator {

    var counter = 0

    private var fragmentManager: FragmentManager? = null

    private val stack = Stack<FragmentModule>()

//    private val childNavigators = HashSet<NavigatorModule>()

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        counter = savedInstanceState?.getInt(INDIVIDUALITY + "COUNTER") ?: counter
        savedInstanceState?.getParcelableArrayList<FragmentModule>(INDIVIDUALITY + "LIST")?.let { savedList ->
            savedList.takeIf { it.isNotEmpty() }
                ?.also {
                    stack.clear()
                    stack.addAll(it)
                    stack.peek()?.also { module ->
                        module.navigatorScopeName = navigatorScopeName
                        module.installModule()
                    }
                }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        log("onSaveInstanceStateNavigator start $outState")
        outState.putInt(INDIVIDUALITY + "COUNTER", counter)
        outState.putParcelableArrayList(INDIVIDUALITY + "LIST", ArrayList(stack))
        log("onSaveInstanceStateNavigator finish $outState")
    }

    override fun forwardIfEmpty(getModule: ICustomizationCommand.() -> FragmentModule) {
        log("forwardIfEmpty $stack")
        if (stack.isEmpty()) forward(getModule)
    }

    override fun forward(getModule: ICustomizationCommand.() -> FragmentModule) {
        log("forward $stack")
        val customizationCommand = CustomizationCommand()
        getModule(customizationCommand).run {
            stack.push(this)
            navigatorScopeName = this@Navigator.navigatorScopeName
            installModule()
            fragmentManager?.show(this, customizationCommand)
        }
        log("forward finish $stack")
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
        fragmentManager?.popBackStack()
//        fragmentManager?.show(stack.peek(), customizationCommand)
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
        fragmentManager?.show(stack.peek(), customizationCommand)
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

//    override fun startNewNavigatorOn(containerId: Int, arguments: Bundle?): INavigator {
//        log("startNewNavigatorOn")
//        val navigatorModule = NavigatorModule(containerId, arguments, null)
////        childNavigators.add(navigatorModule)
//        navigatorModule.installModule()
//        return navigatorModule.navigator
//    }

    override fun attachFragmentManager(fragmentManager: FragmentManager) {
        log("attachFragmentManager $fragmentManager")
        this.fragmentManager = fragmentManager
        stack.takeIf { it.isNotEmpty() }?.peek()?.run {
            fragmentManager.show(this, null)
        }
    }

    override fun detachFragmentManager() {
        log("detachFragmentManager $fragmentManager")
        fragmentManager = null
//        childNavigators.forEach {
//            it.navigator.detachFragmentManager()
//        }
    }

    override fun destroy() {
        log("destroy $stack")
        stack.takeIf { it.isNotEmpty() }?.forEach {
            fragmentManager?.remove(it)
            it.close()
        }
//        childNavigators.forEach { it.close() }
        stack.clear()
        fragmentManager = null
    }

    private fun Stack<FragmentModule>.popAndClose() = pop().close()

    private fun FragmentManager.show(module: FragmentModule, customization: CustomizationCommand?) {
        (module as? DialogFragmentModule)?.also { add(it, customization) }
            ?: findFragmentByTag(module.scopeName)
            ?: run {
                beginTransaction()
                    .customizeTransaction(customization)
                    .replace(containerId, module.createFragment(), module.scopeName)
                    .addToBackStack(module.scopeName)
                    .commit()
            }
    }

    private fun FragmentManager.add(module: DialogFragmentModule, customization: CustomizationCommand?) {
        findFragmentByTag(module.scopeName) ?: run {
            beginTransaction()
                .customizeTransaction(customization)
                .add(module.createFragment(), module.scopeName)
                .addToBackStack(module.scopeName)
                .commit()
        }
    }

    private fun FragmentManager.remove(module: FragmentModule) {
        findFragmentByTag(module.scopeName)?.also {
            beginTransaction()
                .remove(it)
                .commit()
        }
    }

    private fun FragmentTransaction.customizeTransaction(cc: CustomizationCommand?): FragmentTransaction {
        cc?.customize(this)
        return this
    }
}