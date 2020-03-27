package ruf.view.locationmap.sample.detail

import ruf.view.locationmap.sample.common.ExampleSharedModule
import ruf.view.locationmap.sample.list.ListModule
import ruf.view.multi_module_navigation.ParentNavigator
import ruf.view.multi_module_navigation.navigator.INavigatorCommand
import ruf.view.multi_module_navigation.navigator.Navigator
import ruf.view.multi_module_navigation.navigator.backTo
import toothpick.InjectConstructor

@InjectConstructor
class DetailRouter(
    @ParentNavigator private val command: INavigatorCommand,
    private val scopeNameModel: ExampleSharedModule.ScopeNameModel
) {

    fun addDetail(data: DetailData) {
        command.forward {
            DetailModule(
                data.copy(text = "${data.text}+${++(command as Navigator).counter}"),
                scopeNameModel
            )
        }
    }

    fun replace(data: DetailData) {
        command.replace {
            DetailModule(
                data.copy(text = data.text.replaceAfterLast('+', (++(command as Navigator).counter).toString())),
                scopeNameModel
            )
        }
    }

    fun back() = command.back()

    fun closeDetail() = command.backTo<ListModule>()
}