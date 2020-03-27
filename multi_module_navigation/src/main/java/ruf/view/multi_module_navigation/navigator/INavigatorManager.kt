package ruf.view.multi_module_navigation.navigator

import ruf.view.multi_module_navigation.CustomizationCommand
import ruf.view.multi_module_navigation.module.DialogFragmentModule
import ruf.view.multi_module_navigation.module.FragmentModule
import kotlin.reflect.KClass

interface INavigatorManager {

    val navigatorScopeName: String

    fun replace(module: FragmentModule, customization: CustomizationCommand?)

    fun add(module: FragmentModule, customization: CustomizationCommand?)

    fun showDialog(module: DialogFragmentModule, customization: CustomizationCommand?)

    fun popBackStack()

    fun popBackStackTo(kClass: KClass<out FragmentModule>?)
}