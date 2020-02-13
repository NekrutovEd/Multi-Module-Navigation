package ruf.view.locationmap.navigator.list

import android.support.annotation.IdRes
import ruf.view.locationmap.navigator.INavigator
import ruf.view.locationmap.navigator.INavigatorCommand
import ruf.view.locationmap.navigator.detail.DetailData
import ruf.view.locationmap.navigator.detail.DetailModule
import ruf.view.locationmap.navigator.dialog.ExampleDialogModule
import toothpick.InjectConstructor

@InjectConstructor
class ListRouter(private val navigator: INavigatorCommand) {

    fun openDetail() {
        navigator.forward(DetailModule(DetailData("First detail")))
    }

    fun showDialog() {
        navigator.showDialog(ExampleDialogModule())
    }

    fun createNavigator(@IdRes containerId: Int): INavigator {
        return navigator.startNavigatorScope(containerId)
    }
}