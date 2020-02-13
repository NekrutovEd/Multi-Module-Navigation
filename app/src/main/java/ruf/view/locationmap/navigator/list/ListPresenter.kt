package ruf.view.locationmap.navigator.list

import android.support.annotation.IdRes
import ruf.view.locationmap.navigator.INavigator
import ruf.view.locationmap.navigator.IPresenter
import ruf.view.locationmap.navigator.IView
import toothpick.InjectConstructor

@InjectConstructor
class ListPresenter constructor(
    private val router: ListRouter
) : IPresenter {

    private var view: IView? = null

    private var newNavigator: INavigator? = null

    fun openDetail() {
        router.openDetail()
    }

    fun addModule(@IdRes containerId: Int) {
        newNavigator = router.createNavigator(containerId)
        view?.getChildFragmentManager()?.also {
            newNavigator?.attachFragmentManager(it)
        }
        newNavigator?.forward(ListModule())
    }

    fun removeModule() {
        newNavigator?.destroy()
    }

    override fun attachView(view: IView) {
        this.view = view
        view.getChildFragmentManager().also {
            newNavigator?.attachFragmentManager(it)
        }
    }

    override fun detachView() {
        view = null
        newNavigator?.detachFragmentManager()
    }
}