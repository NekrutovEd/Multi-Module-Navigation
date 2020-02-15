package ruf.view.locationmap.sample.detail

import ruf.view.locationmap.navigator.INavigatorCommand
import ruf.view.locationmap.navigator.Navigator
import ruf.view.locationmap.navigator.backTo
import ruf.view.locationmap.sample.list.ListModule
import toothpick.InjectConstructor

@InjectConstructor
class DetailRouter(private val navigator: INavigatorCommand) {

    fun addDetail(data: DetailData) {
        navigator.forward { DetailModule(data.copy(text = "${data.text}+${++(navigator as Navigator).counter}")) }
    }

    fun replace(data: DetailData) {
        navigator.replace {
            DetailModule(
                data.copy(text = data.text.replaceAfterLast('+', (++(navigator as Navigator).counter).toString()))
            )
        }
    }

    fun back() = navigator.back()

    fun closeDetail() = navigator.backTo<ListModule>()
}