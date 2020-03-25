package ruf.view.locationmap.sample.detail

import toothpick.InjectConstructor

// Хотел данные? Получи и распишись.
@InjectConstructor
class DetailPresenter(val data: DetailData, private val router: DetailRouter) {

    fun addDetail() = router.addDetail(data)

    fun replaceDetail() = router.replace(data)

    fun removeDetail() = router.back()

    fun closeDetail() = router.closeDetail()
}