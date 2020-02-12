package ruf.view.locationmap.navigator

import android.support.annotation.IdRes
import android.support.v4.app.FragmentManager
import java.util.*
import kotlin.collections.HashSet
import kotlin.reflect.KClass

@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
class Navigator(@IdRes private val containerId: Int, val navigatorScopeName: Any) : INavigator<FragmentModule> {


    var counter = 0


    private val stack = Stack<FragmentModule>()

    private var fragmentManager: FragmentManager? = null
    private val childNavigators = HashSet<NavigatorModule>()

    override fun forward(module: FragmentModule) {
        module.run {
            stack.push(module)
            navigatorScopeName = this@Navigator.navigatorScopeName
            installModule()
            fragmentManager?.show(this)
        }
    }

    override fun replace(module: FragmentModule) {
        if (!stack.empty()) stack.pop().close()
        forward(module)
    }

    override fun back(): Boolean {
        if (stack.empty()) return false
        stack.pop().close()
        if (stack.empty()) return false
        stack.peek().run {
            installModule()
            fragmentManager?.show(this)
        }
        return true
    }

    override fun <K : KClass<out FragmentModule>> backTo(moduleClass: K): Boolean {
        if (stack.empty()) return false
        val distance = stack.search(stack.find { it::class == moduleClass })
        if (distance <= 1) return false
        for (i in 1 until distance) {
            stack.pop().close()
        }
        stack.peek().run {
            installModule()
            fragmentManager?.show(this)
        }
        return true
    }

    override fun startNavigatorScope(@IdRes containerId: Int): Navigator {
        childNavigators.find { it.containerId == containerId }
            ?.also {
                childNavigators.remove(it)
                it.close()
            }
        val navigatorModule = NavigatorModule(containerId)
        childNavigators.add(navigatorModule)
        navigatorModule.installModule()
        return navigatorModule.navigator
    }

    override fun attachFragmentManager(fragmentManager: FragmentManager) {
        this.fragmentManager = fragmentManager
        stack.takeUnless { it.empty() }?.peek()?.run {
            fragmentManager.show(this)
        }
        childNavigators.forEach {
            it.navigator.attachFragmentManager(fragmentManager)
        }
    }

    override fun detachFragmentManager() {
        fragmentManager = null
        childNavigators.forEach {
            it.navigator.detachFragmentManager()
        }
    }

    override fun destroy() {
        fragmentManager = null
        stack.forEach {
            fragmentManager?.remove(it)
            it.close()
        }
        childNavigators.forEach { it.close() }
        stack.clear()
    }

    private fun FragmentManager.show(module: FragmentModule) {
        findFragmentByTag(module.scopeName) ?: run {
            beginTransaction()
                .replace(containerId, module.fragment, module.scopeName)
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
}