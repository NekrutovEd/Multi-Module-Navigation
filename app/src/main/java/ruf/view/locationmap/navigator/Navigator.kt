package ruf.view.locationmap.navigator

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import java.util.*
import kotlin.collections.HashSet

class Navigator(@IdRes private val containerId: Int, private val fragmentManager: FragmentManager) {


    var counter = 0



    private val stack = Stack<FragmentModule>()

    private val childNavigators = HashSet<NavigatorModule>()

    fun showFragmentScope(module: FragmentModule) {
        module.individuality = containerId.toString()
        stack.push(module)
        module.installModule()
        replace(module.fragment)
    }

    fun popBackStack() {
        stack.pop().close()
        stack.peek().run {
            installModule()
            replace(fragment)
        }
    }

    fun startNavigatorScope(@IdRes containerId: Int): Navigator {
        val navigatorModule = NavigatorModule(containerId, fragmentManager)
        childNavigators.add(navigatorModule)
        navigatorModule.installModule()
        return navigatorModule.navigator
    }

    fun close() {
        childNavigators.forEach {
            it.navigator.close()
            it.close()
        }
    }

    private fun replace(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(containerId, fragment, this::class.java.name + containerId.toString())
            .commit()
    }
}