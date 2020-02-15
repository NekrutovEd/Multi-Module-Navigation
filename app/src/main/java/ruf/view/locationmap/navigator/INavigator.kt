package ruf.view.locationmap.navigator

import android.support.v4.app.FragmentManager
import kotlin.reflect.KClass

interface INavigatorCommand {

    fun forwardIfEmpty(getModule: ICustomizationCommand.() -> FragmentModule)

    fun forward(getModule: ICustomizationCommand.() -> FragmentModule)

    fun replace(getModule: ICustomizationCommand.() -> FragmentModule)

    fun back(customization: ICustomizationCommand.() -> Unit = {}): Boolean

    /** @return | -1 = stack.empty | n = count step back | */
    fun backTo(getModule: ICustomizationCommand.() -> KClass<out FragmentModule>): Int

    fun showDialog(getModule: ICustomizationCommand.() -> DialogFragmentModule)

    fun startNewNavigatorOn(containerId: Int): INavigator
}

interface INavigator : INavigatorCommand {

    fun attachFragmentManager(fragmentManager: FragmentManager)

    fun detachFragmentManager()

    fun destroy()
}

inline fun <reified K : FragmentModule> INavigatorCommand.backTo(
    crossinline customization: ICustomizationCommand.() -> Unit = {}
) = backTo {
        this.customization()
        K::class
    }