package ruf.view.locationmap.library.command

import ruf.view.locationmap.library.CustomizationCommand
import ruf.view.locationmap.library.ICustomizationCommand
import ruf.view.locationmap.library.module.DialogFragmentModule
import ruf.view.locationmap.library.navigator.INavigatorManager

class ShowDialogCommand(private val getDialogModule: ICustomizationCommand.() -> DialogFragmentModule) : ICommand {

    override fun execute(navigatorManager: INavigatorManager) {
        val customizationCommand = CustomizationCommand()
        val module = getDialogModule(customizationCommand)
        module.navigatorScopeName = navigatorManager.navigatorScopeName
        module.installModule()
        navigatorManager.showDialog(module, customizationCommand)
    }
}