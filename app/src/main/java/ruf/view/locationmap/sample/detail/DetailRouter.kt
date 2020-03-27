package ruf.view.locationmap.sample.detail

import ruf.view.feature_detail_presentation.DetailData
import ruf.view.feature_detail_presentation.DetailModule
import ruf.view.feature_detail_presentation.IDetailRouter
import ruf.view.feature_list_presentation.ListModule
import ruf.view.multi_module_navigation.ParentNavigator
import ruf.view.multi_module_navigation.navigator.INavigatorCommand
import ruf.view.multi_module_navigation.navigator.Navigator
import ruf.view.multi_module_navigation.navigator.backTo
import ruf.view.shared_listdata.ExampleSharedModule
import toothpick.InjectConstructor

@InjectConstructor
class DetailRouter(
    @ParentNavigator private val command: INavigatorCommand,
    private val scopeNameModel: ExampleSharedModule.ScopeNameModel
) : IDetailRouter {

    override fun addDetail(data: DetailData) {
        command.forward {
            DetailModule(
                DetailRouterClass,
                data.copy(text = "${data.text}+${++(command as Navigator).counter}"),
                scopeNameModel
            )
        }
    }

    override fun replace(data: DetailData) {
        command.replace {
            DetailModule(
                DetailRouterClass,
                data.copy(text = data.text.replaceAfterLast('+', (++(command as Navigator).counter).toString())),
                scopeNameModel
            )
        }
    }

    override fun back() = command.back()

    override fun closeDetail() = command.backTo<ListModule>()
}