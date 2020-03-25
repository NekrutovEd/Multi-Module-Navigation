package ruf.view.locationmap.library.command

import ruf.view.locationmap.library.module.FragmentModule
import ruf.view.locationmap.library.navigator.INavigatorManager
import kotlin.reflect.KClass

class BackToCommand(private val kClass: KClass<out FragmentModule>?) : ICommand {

    override fun execute(navigatorManager: INavigatorManager) {
        navigatorManager.popBackStackTo(kClass)
    }
}