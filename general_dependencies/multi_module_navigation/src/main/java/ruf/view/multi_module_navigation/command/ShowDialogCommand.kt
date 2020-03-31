package ruf.view.multi_module_navigation.command

import ruf.view.multi_module_navigation.CustomizationCommand
import ruf.view.multi_module_navigation.ICustomizationCommand
import ruf.view.multi_module_navigation.module.DialogFragmentModule
import ruf.view.multi_module_navigation.navigator.INavigatorManager

class ShowDialogCommand(private val getDialogModule: ICustomizationCommand.() -> DialogFragmentModule) : ICommand {

    override fun execute(navigatorManager: INavigatorManager) {
        val customizationCommand = CustomizationCommand()
        val module = getDialogModule(customizationCommand)
        module.parentScopeIdentifier = navigatorManager.navigatorScopeIdentifier
        module.installModule()
        navigatorManager.showDialog(module, customizationCommand)
    }
}