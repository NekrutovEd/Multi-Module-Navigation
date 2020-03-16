package ruf.view.locationmap.sample.detail

import ruf.view.locationmap.navigator.INavigatorCommand
import ruf.view.locationmap.navigator.Navigator
import ruf.view.locationmap.navigator.backTo
import ruf.view.locationmap.sample.ParentNavigator
import ruf.view.locationmap.sample.list.ListModule
import toothpick.InjectConstructor

@InjectConstructor
class DetailRouter(@ParentNavigator private val command: INavigatorCommand) {

    fun addDetail(data: DetailData) {
        command.forward { DetailModule(data.copy(text = "${data.text}+${++(command as Navigator).counter}")) }
    }

    fun replace(data: DetailData) {
        command.replace {
            DetailModule(
                data.copy(text = data.text.replaceAfterLast('+', (++(command as Navigator).counter).toString()))
            )
        }
    }

    fun back() = command.back()

    fun closeDetail() = command.backTo<ListModule>()
}