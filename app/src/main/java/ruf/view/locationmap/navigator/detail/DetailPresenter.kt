package ruf.view.locationmap.navigator.detail

import toothpick.InjectConstructor

@InjectConstructor
class DetailPresenter(
    val data: DetailData,
    private val router: DetailRouter
) {

    fun addDetail() {
        router.addDetail(data)
    }

    fun closeDetail() {
        router.closeDetail()
    }
}
