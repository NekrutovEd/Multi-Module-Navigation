package ruf.view.locationmap.navigator.list

import android.support.annotation.IdRes
import ruf.view.locationmap.navigator.Navigator
import ruf.view.locationmap.navigator.detail.DetailData
import ruf.view.locationmap.navigator.detail.DetailModule
import toothpick.InjectConstructor

@InjectConstructor
class ListRouter(private val navigator: Navigator) {

    fun openDetail() {
        navigator.forward(DetailModule(DetailData("First detail")))
    }

    fun createNavigator(@IdRes containerId: Int): Navigator {
        return navigator.startNavigatorScope(containerId)
    }
}