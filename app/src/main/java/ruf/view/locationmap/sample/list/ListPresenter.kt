package ruf.view.locationmap.sample.list

import ruf.view.locationmap.sample.IPresenter
import ruf.view.locationmap.sample.IView
import toothpick.InjectConstructor

@InjectConstructor
class ListPresenter(
    val data: ListData,
    private val router: ListRouter
) : IPresenter {

    var counter: Int = 0

    private var view: IView? = null

    override fun attachView(view: IView) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    fun openDetail() = router.openDetail()

    fun addModule() = router.addListModule(data.tag + counter++)

    fun removeModule() = router.removeListModule()

    fun showDialog() = router.showDialog()
}