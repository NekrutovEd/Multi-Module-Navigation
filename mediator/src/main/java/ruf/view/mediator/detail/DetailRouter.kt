package ruf.view.mediator.detail

import ruf.view.feature_detail_presentation.DetailData
import ruf.view.feature_detail_presentation.DetailModule
import ruf.view.feature_detail_presentation.IDetailRouter
import ruf.view.feature_list_presentation.ListModule
import ruf.view.multi_module_navigation.ParentNavigator
import ruf.view.multi_module_navigation.navigator.INavigator
import ruf.view.multi_module_navigation.navigator.ICommanderNavigator
import ruf.view.multi_module_navigation.navigator.backTo
import ruf.view.shared_listdata.ExampleSharedModule
import toothpick.InjectConstructor

@InjectConstructor
class DetailRouter(
    @ParentNavigator private val commander: ICommanderNavigator,
    private val scopeNameModel: ExampleSharedModule.ScopeNameModel
) : IDetailRouter {

    override fun addDetail(data: DetailData) {
        commander.forward {
            val newData = data.copy(text = "${data.text}+${++(commander as INavigator).counter}")
            DetailModule(DetailRouterClass, newData, scopeNameModel)
        }
    }

    override fun replace(data: DetailData) {
        commander.replace {
            val newData = data.copy(text = data.text.replaceAfterLast('+', (++(commander as INavigator).counter).toString()))
            DetailModule(DetailRouterClass, newData, scopeNameModel)
        }
    }

    override fun back() = commander.back()

    override fun closeDetail() = commander.backTo<ListModule>()
}