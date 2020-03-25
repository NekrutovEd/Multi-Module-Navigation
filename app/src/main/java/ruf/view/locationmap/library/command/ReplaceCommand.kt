package ruf.view.locationmap.library.command

import ruf.view.locationmap.library.ICustomizationCommand
import ruf.view.locationmap.library.module.FragmentModule
import ruf.view.locationmap.library.navigator.INavigatorManager

class ReplaceCommand(private val getModule: ICustomizationCommand.() -> FragmentModule) : ICommand {

    override fun execute(navigatorManager: INavigatorManager) {
        BackCommand().execute(navigatorManager)
        ForwardCommand(getModule).execute(navigatorManager)
    }
}