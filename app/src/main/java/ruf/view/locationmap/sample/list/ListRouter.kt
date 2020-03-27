package ruf.view.locationmap.sample.list

import ruf.view.feature_list_presentation.IListRouter
import ruf.view.feature_list_presentation.ListModule
import ruf.view.feature_list_presentation.ListNavigator
import ruf.view.locationmap.R
import ruf.view.locationmap.sample.detail.DetailData
import ruf.view.locationmap.sample.detail.DetailModule
import ruf.view.locationmap.sample.dialog.ExampleDialogModule
import ruf.view.multi_module_navigation.ParentNavigator
import ruf.view.multi_module_navigation.navigator.INavigatorCommand
import ruf.view.shared_listdata.ExampleSharedModule
import toothpick.InjectConstructor

@InjectConstructor
class ListRouter(
    @ParentNavigator private val command: INavigatorCommand,
    @ListNavigator private val commandList: INavigatorCommand,
    private val scopeNameModel: ExampleSharedModule.ScopeNameModel
) : IListRouter {

    override fun openDetail() = command.forward {
        setCustomAnimations(
            R.anim.first_list_enter,
            R.anim.first_list_exit,
            R.anim.first_list_enter,
            R.anim.first_list_exit
        )
        DetailModule(DetailData("First detail"), scopeNameModel)
    }

    override fun showDialog() = command.showDialog { ExampleDialogModule() }

    override fun addListModule(tag: String) {
        commandList.backTo(null)
        commandList.forward {
            setCustomAnimations(
                R.anim.first_list_enter,
                R.anim.first_list_exit,
                R.anim.first_list_enter,
                R.anim.first_list_exit
            )
            ListModule(ListRouterProvider, tag)
        }
    }

    override fun removeListModule() = commandList.back()
}
