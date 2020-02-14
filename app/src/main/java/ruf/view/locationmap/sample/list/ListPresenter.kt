package ruf.view.locationmap.sample.list

import android.support.annotation.IdRes
import ruf.view.locationmap.navigator.INavigator
import ruf.view.locationmap.sample.IPresenter
import ruf.view.locationmap.sample.IView
import toothpick.InjectConstructor

@InjectConstructor
class ListPresenter(private val router: ListRouter) : IPresenter {

    private var view: IView? = null

    private var newNavigator: INavigator? = null

    override fun attachView(view: IView) {
        this.view = view
        view.getChildFragmentManager().also {
            newNavigator?.attachFragmentManager(it)
        }
    }

    override fun detachView() {
        view = null
    }

    fun openDetail() = router.openDetail()

    fun addModule(@IdRes containerId: Int) {
        newNavigator = router.createNavigator(containerId)
        newNavigator?.forward(ListModule())
        view?.getChildFragmentManager()?.also {
            newNavigator?.attachFragmentManager(it)
        }
    }

    fun showDialog() = router.showDialog()

    fun removeModule() = newNavigator?.destroy()
}