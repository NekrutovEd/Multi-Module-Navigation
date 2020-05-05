package com.accioblogger.feature_example_box.feature_module_pager

import kotlinx.android.parcel.Parcelize
import ruf.view.core.IRouterClass
import ruf.view.multi_module_navigation.module.FragmentModule
import ruf.view.multi_module_navigation.module.ScopeModule
import toothpick.ktp.binding.bind
import javax.inject.Provider

@Parcelize
class PagerModule(
    private val routerClass: IRouterClass<IPagerRouter>,
    override val scopeIdentifier: ScopeIdentifier = randomScopeIdentifier<PagerModule>()
) : FragmentModule(PagerFragment::class), Provider<ScopeModule.ScopeIdentifier> {

    init {
        bind<IPagerRouter>().toClass(routerClass.kClass)
        bind<ScopeIdentifier>().withName(PagerIdentifier::class).toProviderInstance(this)
        bind<PagerPresenter>().toClass<PagerPresenter>()
    }

    override fun get(): ScopeIdentifier = scopeIdentifier
}
