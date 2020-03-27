package ruf.view.feature_detail_presentation

import ruf.view.shared_listdata.ListData
import toothpick.InjectConstructor

@InjectConstructor
internal class DetailPresenter(
    val data: DetailData,
    val listData: ListData,
    private val router: IDetailRouter
) {

    fun addDetail() = router.addDetail(data)

    fun replaceDetail() = router.replace(data)

    fun removeDetail() = router.back()

    fun closeDetail() = router.closeDetail()
}