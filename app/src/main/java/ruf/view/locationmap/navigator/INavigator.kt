package ruf.view.locationmap.navigator

import android.support.v4.app.FragmentManager
import kotlin.reflect.KClass

interface INavigatorCommand {

    fun forwardIfEmpty(module: FragmentModule)

    fun forward(module: FragmentModule)

    fun replace(module: FragmentModule)

    fun back(): Boolean

    /** @return | -1 = stack.empty | n = count step back | */
    fun <K : KClass<out FragmentModule>> backTo(moduleClass: K): Int

    fun showDialog(module: DialogFragmentModule)

    fun startNavigatorScope(containerId: Int): INavigator
}

interface INavigator : INavigatorCommand {
    fun attachFragmentManager(fragmentManager: FragmentManager)

    fun detachFragmentManager()

    fun destroy()
}
