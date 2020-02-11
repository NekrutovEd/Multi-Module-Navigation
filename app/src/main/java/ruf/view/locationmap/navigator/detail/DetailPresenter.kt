package ruf.view.locationmap.navigator.detail

import ruf.view.locationmap.navigator.IPresenter
import ruf.view.locationmap.navigator.IView
import toothpick.InjectConstructor

@InjectConstructor
class DetailPresenter(
    val data: DetailData,
    private val router: DetailRouter
) : IPresenter {

    fun addDetail() {
        router.addDetail(data)
    }

    fun replaceDetail() {
        router.replace(data)
    }

    fun removeDetail() {
        router.back()
    }

    fun closeDetail() {
        router.closeDetail()
    }

    override fun attachView(view: IView) {
    }

    override fun detachView() {
    }
}
