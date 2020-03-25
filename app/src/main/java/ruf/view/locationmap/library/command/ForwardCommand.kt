package ruf.view.locationmap.library.command

import ruf.view.locationmap.library.CustomizationCommand
import ruf.view.locationmap.library.ICustomizationCommand
import ruf.view.locationmap.library.module.FragmentModule
import ruf.view.locationmap.library.navigator.INavigatorManager

class ForwardCommand(private val getModule: ICustomizationCommand.() -> FragmentModule) : ICommand {

    override fun execute(navigatorManager: INavigatorManager) {
        val customizationCommand = CustomizationCommand()
        val module = getModule(customizationCommand)
        module.navigatorScopeName = navigatorManager.navigatorScopeName
        module.installModule()
        navigatorManager.replace(module, customizationCommand)
    }
}