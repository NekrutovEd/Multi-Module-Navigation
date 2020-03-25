package ruf.view.locationmap.library.command

import ruf.view.locationmap.library.navigator.INavigatorManager

interface ICommand {

    fun execute(navigatorManager: INavigatorManager)
}