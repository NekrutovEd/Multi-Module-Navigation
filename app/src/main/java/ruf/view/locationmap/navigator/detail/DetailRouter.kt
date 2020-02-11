package ruf.view.locationmap.navigator.detail

import ruf.view.locationmap.navigator.Navigator
import toothpick.InjectConstructor

@InjectConstructor
class DetailRouter(private val navigator: Navigator) {

    fun addDetail(data: DetailData) {
        navigator.showFragmentScope(DetailModule(data.copy(text = "${data.text}+${++navigator.counter}")))
    }

    fun closeDetail() {
        navigator.popBackStack()
//        navigator.popBackStack()
    }
}