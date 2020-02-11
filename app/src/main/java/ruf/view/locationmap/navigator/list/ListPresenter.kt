package ruf.view.locationmap.navigator.list

import ruf.view.locationmap.navigator.Navigator
import toothpick.InjectConstructor

@InjectConstructor
class ListPresenter constructor(
    private val router: ListRouter
) {

    private var newNavigator: Navigator? = null

    fun openDetail() {
        router.openDetail()
    }

    fun addDetail() {
        newNavigator = router.createNavigator()
    }

    fun stop() {
        newNavigator?.close()
    }

}