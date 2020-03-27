package ruf.view.multi_module_navigation.command

import ruf.view.multi_module_navigation.module.FragmentModule
import ruf.view.multi_module_navigation.navigator.INavigatorManager
import kotlin.reflect.KClass

class BackToCommand(private val kClass: KClass<out FragmentModule>?) : ICommand {

    override fun execute(navigatorManager: INavigatorManager) {
        navigatorManager.popBackStackTo(kClass)
    }
}