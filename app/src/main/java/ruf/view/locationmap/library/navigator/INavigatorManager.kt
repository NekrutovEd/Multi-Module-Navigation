package ruf.view.locationmap.library.navigator

import ruf.view.locationmap.library.CustomizationCommand
import ruf.view.locationmap.library.module.DialogFragmentModule
import ruf.view.locationmap.library.module.FragmentModule
import kotlin.reflect.KClass

interface INavigatorManager {

    val navigatorScopeName: String

    fun replace(module: FragmentModule, customization: CustomizationCommand?)

    fun add(module: FragmentModule, customization: CustomizationCommand?)

    fun showDialog(module: DialogFragmentModule, customization: CustomizationCommand?)

    fun popBackStack()

    fun popBackStackTo(kClass: KClass<out FragmentModule>?)
}