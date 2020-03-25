package ruf.view.locationmap.library.command

import ruf.view.locationmap.library.navigator.INavigatorManager

class BackCommand : ICommand {

    override fun execute(navigatorManager: INavigatorManager) = navigatorManager.popBackStack()
}