package ruf.view.multi_module_navigation.navigator

import ruf.view.multi_module_navigation.ICustomizer
import ruf.view.multi_module_navigation.module.DialogFragmentModule
import ruf.view.multi_module_navigation.module.FragmentModule
import ruf.view.multi_module_navigation.module.ScopeModule
import kotlin.reflect.KClass

interface INavigatorManager {

    val navigatorScopeIdentifier: ScopeModule.ScopeIdentifier

    fun replace(module: FragmentModule, customizer: ICustomizer?)

    fun add(module: FragmentModule, customizer: ICustomizer?)

    fun showDialog(module: DialogFragmentModule, customizer: ICustomizer?)

    fun popBackStack()

    /** @return | -1 = stack.empty | n = count step back | */
    fun popBackStackTo(kClass: KClass<out FragmentModule>?): Int
}