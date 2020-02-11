package ruf.view.locationmap.navigator.list

import ruf.view.locationmap.navigator.IPresenter
import ruf.view.locationmap.navigator.IView
import ruf.view.locationmap.navigator.Navigator
import toothpick.InjectConstructor

@InjectConstructor
class ListPresenter constructor(
    private val router: ListRouter
) : IPresenter {

    private var newNavigator: Navigator? = null

    fun openDetail() {
        router.openDetail()
    }

    fun addDetail() {
        newNavigator = router.createNavigator()
    }

    override fun attachView(view: IView) {
        newNavigator?.restart()
    }

    override fun detachView() {
        newNavigator?.close()
    }
}