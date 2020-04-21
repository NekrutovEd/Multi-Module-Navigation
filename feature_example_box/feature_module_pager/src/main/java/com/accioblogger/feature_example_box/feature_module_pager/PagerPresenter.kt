package com.accioblogger.feature_example_box.feature_module_pager

import ruf.view.core.IPresenter
import ruf.view.core.IView
import toothpick.InjectConstructor

@InjectConstructor
class PagerPresenter(private val router: IPagerRouter) : IPresenter {

    override fun attachView(view: IView) {}

    override fun detachView() {}

    fun getPagerModules() = router.getPagerModule()
}
