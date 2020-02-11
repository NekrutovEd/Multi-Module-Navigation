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

    fun forward(module: FragmentModule) {
        module.individuality = containerId.toString()
        stack.push(module)
        module.close()
        module.installModule()
        replace(module.fragment)
    }

    fun replace(module: FragmentModule) {
        if (!stack.empty()) stack.pop().close()
        forward(module)
    }

    fun back(): Boolean {
        if (stack.empty()) return false
        stack.pop().close()
        if (stack.empty()) return false
        stack.peek().run {
            installModule()
            replace(fragment)
        }
        return true
    }

    fun backTo(module: FragmentModule): Boolean {
        if (stack.empty()) return false
        val distance = stack.search(module)
        if (distance <= 1) return false
        for (i in 1 until distance) {
            stack.pop().close()
        }
        stack.peek().run {
            installModule()
            replace(fragment)
        }
        return true
    }

    fun startNavigatorScope(@IdRes containerId: Int): Navigator {
        val navigatorModule = NavigatorModule(containerId, fragmentManager)
        childNavigators.add(navigatorModule)
        navigatorModule.close()
        navigatorModule.installModule()
        return navigatorModule.navigator
    }

    fun restart() {
        stack.peek().run {
            installModule()
            replace(fragment)
        }
        childNavigators.forEach {
            it.installModule()
            it.navigator.restart()
        }
    }

    fun close() {
        childNavigators.forEach {
            it.navigator.close()
            it.close()
        }
        stack.forEach { it.close() }
    }

    private fun replace(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(containerId, fragment, this::class.java.name + containerId.toString())
            .commit()
    }
}