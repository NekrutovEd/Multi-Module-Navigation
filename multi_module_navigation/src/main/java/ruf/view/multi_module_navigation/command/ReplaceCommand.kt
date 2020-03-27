package ruf.view.multi_module_navigation.command

import ruf.view.multi_module_navigation.ICustomizationCommand
import ruf.view.multi_module_navigation.module.FragmentModule
import ruf.view.multi_module_navigation.navigator.INavigatorManager

class ReplaceCommand(private val getModule: ICustomizationCommand.() -> FragmentModule) : ICommand {

    override fun execute(navigatorManager: INavigatorManager) {
        BackCommand().execute(navigatorManager)
        ForwardCommand(getModule).execute(navigatorManager)
    }
}