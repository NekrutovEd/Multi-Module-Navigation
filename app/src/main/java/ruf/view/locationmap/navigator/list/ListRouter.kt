package ruf.view.locationmap.navigator.list

import ruf.view.locationmap.R
import ruf.view.locationmap.navigator.Navigator
import ruf.view.locationmap.navigator.detail.DetailData
import ruf.view.locationmap.navigator.detail.DetailModule
import toothpick.InjectConstructor

@InjectConstructor
class ListRouter(private val navigator: Navigator) {

    fun openDetail() {
        navigator.forward(DetailModule(DetailData("First detail")))
    }

    fun createNavigator(): Navigator {
        return navigator.startNavigatorScope(R.id.child_container).apply { forward(ListModule()) }
    }
}