package ruf.view.locationmap.sample.list

import ruf.view.locationmap.R
import ruf.view.locationmap.library.navigator.INavigatorCommand
import ruf.view.locationmap.sample.ListNavigator
import ruf.view.locationmap.sample.ParentNavigator
import ruf.view.locationmap.sample.detail.DetailData
import ruf.view.locationmap.sample.detail.DetailModule
import ruf.view.locationmap.sample.dialog.ExampleDialogModule
import toothpick.InjectConstructor

@InjectConstructor
class ListRouter(
    @ParentNavigator private val command: INavigatorCommand,
    @ListNavigator private val commandList: INavigatorCommand
) {

    fun openDetail() = command.forward {
        setCustomAnimations(R.anim.first_list_enter, R.anim.first_list_exit, R.anim.first_list_enter, R.anim.first_list_exit)
        DetailModule(DetailData("First detail"))
    }
    fun showDialog() = command.showDialog { ExampleDialogModule() }

    fun addListModule(tag: String) {
        commandList.backTo(null)
        commandList.forward {
            setCustomAnimations(R.anim.first_list_enter, R.anim.first_list_exit, R.anim.first_list_enter, R.anim.first_list_exit)
            ListModule(tag)
        }
    }

    fun removeListModule() = commandList.back()
}
