package ruf.view.locationmap.sample.detail

import ruf.view.shared_listdata.ListData
import toothpick.InjectConstructor

@InjectConstructor
class DetailPresenter(
    val data: DetailData,
    val listData: ListData,
    private val router: DetailRouter
) {

    fun addDetail() = router.addDetail(data)

    fun replaceDetail() = router.replace(data)

    fun removeDetail() = router.back()

    fun closeDetail() = router.closeDetail()
}