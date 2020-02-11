package ruf.view.locationmap.navigator

interface IPresenter {

    fun attachView(view: IView)

    fun detachView()
}