package ruf.view.core

interface IPresenter {

    fun attachView(view: IView)

    fun detachView()
}