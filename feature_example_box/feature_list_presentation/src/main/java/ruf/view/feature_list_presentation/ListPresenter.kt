package ruf.view.feature_list_presentation

import ruf.view.core.BasePresenter
import ruf.view.core.IPresenter
import ruf.view.core.IView
import ruf.view.shared_listdata.ListData
import toothpick.InjectConstructor

@InjectConstructor
internal class ListPresenter(
    val data: ListData,
    private val router: IListRouter
) : BasePresenter() {

    private var counter: Int = 0

    fun openDetail() = router.openDetail()

    fun addModule() = router.addListModule(data.textData + counter++)

    fun removeModule() = router.removeListModule()

    fun showDialog() = router.showDialog()
}