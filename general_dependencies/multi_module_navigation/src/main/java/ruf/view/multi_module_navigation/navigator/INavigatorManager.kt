package ruf.view.multi_module_navigation.navigator

import ruf.view.multi_module_navigation.ICustomizer
import ruf.view.multi_module_navigation.module.DialogFragmentModule
import ruf.view.multi_module_navigation.module.FragmentModule
import kotlin.reflect.KClass

interface INavigatorManager {

    val navigatorScopeName: String

    fun replace(module: FragmentModule, customizer: ICustomizer?)

    fun add(module: FragmentModule, customizer: ICustomizer?)

    fun showDialog(module: DialogFragmentModule, customizer: ICustomizer?)

    fun popBackStack()

    fun popBackStackTo(kClass: KClass<out FragmentModule>?)
}