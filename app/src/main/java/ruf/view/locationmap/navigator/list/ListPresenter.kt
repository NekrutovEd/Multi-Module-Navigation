package ruf.view.locationmap.navigator.list

import android.support.annotation.IdRes
import ruf.view.locationmap.navigator.IPresenter
import ruf.view.locationmap.navigator.IView
import ruf.view.locationmap.navigator.Navigator
import toothpick.InjectConstructor

@InjectConstructor
class ListPresenter constructor(
    private val router: ListRouter
) : IPresenter {

    private var view: IView? = null

    private var newNavigator: Navigator? = null

    fun openDetail() {
        router.openDetail()
    }

    fun addModule(@IdRes containerId: Int) {
        newNavigator = router.createNavigator(containerId)
        view?.manager?.also {
            newNavigator?.attachFragmentManager(it)
        }
        newNavigator?.forward(ListModule())
    }

    fun removeModule() {
        newNavigator?.destroy()
        (view as? ListFragment)?.deleteChild()
    }

    override fun attachView(view: IView) {
        this.view = view
        view.manager?.also {
            newNavigator?.attachFragmentManager(it)
        }
    }

    override fun detachView() {
        view = null
        newNavigator?.detachFragmentManager()
    }
}