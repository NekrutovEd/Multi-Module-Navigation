package ruf.view.core

abstract class BasePresenter : IPresenter {

    protected var view: IView? = null

    final override fun attachView(view: IView) {
        this.view = view
        onAttachedView()
    }

    final override fun detachView() {
        this.view = null
        onDetachedView()
    }

    protected fun onAttachedView() {}

    protected fun onDetachedView() {}
}