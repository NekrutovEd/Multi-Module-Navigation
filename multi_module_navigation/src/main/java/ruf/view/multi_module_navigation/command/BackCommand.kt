package ruf.view.multi_module_navigation.command

import ruf.view.multi_module_navigation.navigator.INavigatorManager

class BackCommand : ICommand {

    override fun execute(navigatorManager: INavigatorManager) = navigatorManager.popBackStack()
}