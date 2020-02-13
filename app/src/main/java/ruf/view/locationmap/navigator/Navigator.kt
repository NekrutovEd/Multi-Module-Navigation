package ruf.view.locationmap.navigator

import android.support.annotation.IdRes
import android.support.annotation.MainThread
import android.support.v4.app.FragmentManager
import java.util.*
import kotlin.collections.HashSet
import kotlin.reflect.KClass

@MainThread
class Navigator(@IdRes private val containerId: Int, val navigatorScopeName: Any) : INavigator {

    var counter = 0

    private var fragmentManager: FragmentManager? = null

    private val stack = Stack<FragmentModule>()
    private val childNavigators = HashSet<NavigatorModule>()

    override fun forwardIfEmpty(module: FragmentModule) {
        if (stack.isEmpty()) forward(module)
    }

    override fun forward(module: FragmentModule) {
        module.run {
            stack.push(module)
            navigatorScopeName = this@Navigator.navigatorScopeName
            installModule()
            fragmentManager?.show(this)
        }
    }

    override fun replace(module: FragmentModule) {
        if (stack.isNotEmpty()) stack.popAndClose()
        forward(module)
    }

    override fun back(): Boolean {
        if (stack.isEmpty()) return false
        stack.popAndClose()
        if (stack.isEmpty()) return false
        fragmentManager?.show(stack.peek())
        return true
    }

    override fun <K : KClass<out FragmentModule>> backTo(moduleClass: K): Int {
        if (stack.isEmpty()) return -1
        val distance = stack.search(stack.findLast { it::class == moduleClass })
        if (distance <= 1) return 0
        for (i in 1 until distance) {
            stack.popAndClose()
        }
        fragmentManager?.show(stack.peek())
        return distance - 1
    }

    override fun startNavigatorScope(containerId: Int): INavigator {
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
        stack.takeIf { it.isNotEmpty() }?.peek()?.run {
            fragmentManager.show(this)
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
            fragmentManager?.remove(it)
            it.close()
        }
        childNavigators.forEach { it.close() }
        stack.clear()
        fragmentManager = null
    }

    inline fun <reified K : FragmentModule> backTo() = backTo(K::class)

    private fun Stack<FragmentModule>.popAndClose() = pop().close()

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