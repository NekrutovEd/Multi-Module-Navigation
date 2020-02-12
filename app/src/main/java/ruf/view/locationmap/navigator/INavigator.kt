package ruf.view.locationmap.navigator

import android.support.annotation.IdRes
import android.support.v4.app.FragmentManager
import kotlin.reflect.KClass

interface INavigator<in T> {

    fun forward(t: T)

    fun replace(t: T)

    fun back(): Boolean

    fun <K : KClass<out T>> backTo(kClassT: K): Boolean

    fun startNavigatorScope(@IdRes containerId: Int): INavigator<T>

    fun attachFragmentManager(fragmentManager: FragmentManager)

    fun detachFragmentManager()

    fun destroy()
}
