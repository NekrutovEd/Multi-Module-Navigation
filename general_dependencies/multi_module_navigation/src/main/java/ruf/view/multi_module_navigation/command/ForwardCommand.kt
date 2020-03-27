package ruf.view.multi_module_navigation.command

import ruf.view.multi_module_navigation.CustomizationCommand
import ruf.view.multi_module_navigation.ICustomizationCommand
import ruf.view.multi_module_navigation.module.FragmentModule
import ruf.view.multi_module_navigation.navigator.INavigatorManager

class ForwardCommand(private val getModule: ICustomizationCommand.() -> FragmentModule) : ICommand {

    override fun execute(navigatorManager: INavigatorManager) {
        val customizationCommand = CustomizationCommand()
        val module = getModule(customizationCommand)
        module.navigatorScopeName = navigatorManager.navigatorScopeName
        module.installModule()
        navigatorManager.replace(module, customizationCommand)
    }
}