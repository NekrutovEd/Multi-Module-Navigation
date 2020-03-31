package ruf.view.core

abstract class BaseFragment : LogFragment(), IView {

    protected abstract val presenter: IPresenter

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }
}