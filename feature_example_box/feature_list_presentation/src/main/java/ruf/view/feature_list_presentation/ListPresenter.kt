package ruf.view.feature_list_presentation

import ruf.view.core.IPresenter
import ruf.view.core.IView
import ruf.view.shared_listdata.ListData
import toothpick.InjectConstructor

@InjectConstructor
internal class ListPresenter(
    val data: ListData,
    private val router: IListRouter
) : IPresenter {

    private var counter: Int = 0

    private var view: IView? = null

    override fun attachView(view: IView) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    fun openDetail() = router.openDetail()

    fun addModule() = router.addListModule(data.textData + counter++)

    fun removeModule() = router.removeListModule()

    fun showDialog() = router.showDialog()
}