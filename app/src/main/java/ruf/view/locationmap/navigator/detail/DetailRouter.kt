package ruf.view.locationmap.navigator.detail

import ruf.view.locationmap.navigator.Navigator
import ruf.view.locationmap.navigator.list.ListModule
import toothpick.InjectConstructor

@InjectConstructor
class DetailRouter(private val navigator: Navigator) {

    fun addDetail(data: DetailData) {
        navigator.forward(DetailModule(data.copy(text = "${data.text}+${++navigator.counter}")))
    }

    fun replace(data: DetailData) {
        navigator.replace(
            DetailModule(
                data.copy(text = data.text.replaceAfterLast('+', (++navigator.counter).toString()))
            )
        )
    }

    fun back() {
        navigator.back()
    }

    fun closeDetail() {
        navigator.backTo<ListModule>()
    }
}