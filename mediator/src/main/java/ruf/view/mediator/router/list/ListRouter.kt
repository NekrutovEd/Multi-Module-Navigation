package ruf.view.mediator.router.list

import kotlinx.android.parcel.Parcelize
import ruf.view.core.IRouterClass
import ruf.view.feature_detail_presentation.DetailData
import ruf.view.feature_detail_presentation.DetailModule
import ruf.view.feature_dialog.ExampleDialogModule
import ruf.view.feature_list_presentation.IListRouter
import ruf.view.feature_list_presentation.ListModule
import ruf.view.feature_list_presentation.ListNavigator
import ruf.view.mediator.R
import ruf.view.mediator.router.detail.DetailRouter
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
        DetailModule(DetailRouter.Class, DetailData("First detail"), exampleSharedIdentifier)
    }

    override fun showDialog() = parentCommander.showDialog { ExampleDialogModule() }

    override fun addListModule(tag: String) {
        listCommander.backTo(null)
        listCommander.forward {
            setCustomAnimations(
                R.anim.first_list_enter, R.anim.first_list_exit,
                R.anim.first_list_enter, R.anim.first_list_exit
            )
            ListModule(Class, tag)
//            DetailModule(DetailRouter.Class, DetailData("List detail"), exampleSharedIdentifier)
        }
    }

    override fun removeListModule() = listCommander.back()


    @Parcelize
    object Class : IRouterClass<ListRouter> {
        override val kClass get() = ListRouter::class
    }
}
