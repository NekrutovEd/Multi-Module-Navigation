package ruf.view.locationmap.sample.list

import android.support.annotation.IdRes
import ruf.view.locationmap.navigator.INavigator
import ruf.view.locationmap.navigator.INavigatorCommand
import ruf.view.locationmap.sample.detail.DetailData
import ruf.view.locationmap.sample.detail.DetailModule
import ruf.view.locationmap.sample.dialog.ExampleDialogModule
import toothpick.InjectConstructor

@InjectConstructor
class ListRouter(private val navigator: INavigatorCommand) {

    fun openDetail() = navigator.forward(DetailModule(DetailData("First detail")))

    fun showDialog() = navigator.showDialog(ExampleDialogModule())

    fun createNavigator(@IdRes containerId: Int): INavigator = navigator.startNewNavigatorOn(containerId)
}