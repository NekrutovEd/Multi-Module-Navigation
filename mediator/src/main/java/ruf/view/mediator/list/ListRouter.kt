package ruf.view.mediator.list

import ruf.view.feature_detail_presentation.DetailData
import ruf.view.feature_detail_presentation.DetailModule
import ruf.view.feature_dialog.ExampleDialogModule
import ruf.view.feature_list_presentation.IListRouter
import ruf.view.feature_list_presentation.ListModule
import ruf.view.feature_list_presentation.ListNavigator
import ruf.view.mediator.R
import ruf.view.mediator.detail.DetailRouterClass
import ruf.view.multi_module_navigation.ParentNavigator
import ruf.view.multi_module_navigation.navigator.ICommanderNavigator
import ruf.view.shared_listdata.ExampleSharedModule
import toothpick.InjectConstructor

@InjectConstructor
class ListRouter(
    @ParentNavigator private val parentCommander: ICommanderNavigator,
    @ListNavigator private val listCommander: ICommanderNavigator,
    private val exampleSharedIdentifier: ExampleSharedModule.ExampleSharedIdentifier
) : IListRouter {

    override fun openDetail() = parentCommander.forward {
        setCustomAnimations(
            R.anim.first_list_enter, R.anim.first_list_exit,
            R.anim.first_list_enter, R.anim.first_list_exit
        )
        DetailModule(DetailRouterClass, DetailData("First detail"), exampleSharedIdentifier)
    }

    override fun showDialog() = parentCommander.showDialog { ExampleDialogModule() }

    override fun addListModule(tag: String) {
        listCommander.backTo(null)
        listCommander.forward {
            setCustomAnimations(
                R.anim.first_list_enter, R.anim.first_list_exit,
                R.anim.first_list_enter, R.anim.first_list_exit
            )
            ListModule(ListRouterClass, tag)
//            DetailModule(DetailRouterClass, DetailData("List detail"), exampleSharedIdentifier)
        }
    }

    override fun removeListModule() = listCommander.back()
}
