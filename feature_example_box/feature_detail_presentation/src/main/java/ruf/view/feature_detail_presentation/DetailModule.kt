package ruf.view.feature_detail_presentation

import kotlinx.android.parcel.Parcelize
import ruf.view.core.IRouterClass
import ruf.view.multi_module_navigation.module.FragmentModule
import ruf.view.shared_listdata.ExampleSharedModule
import ruf.view.shared_listdata.ListData
import toothpick.ktp.binding.bind

@Parcelize
data class DetailModule(
    private val routerClass: IRouterClass<IDetailRouter>,
    private val data: DetailData,
    private val exampleSharedIdentifier: ExampleSharedModule.ExampleSharedIdentifier,
    override val scopeIdentifier: ScopeIdentifier = randomScopeIdentifier<DetailModule>()
) : FragmentModule(DetailFragment::class) {

    init {
        bind<ExampleSharedModule.ExampleSharedIdentifier>().toInstance(exampleSharedIdentifier)
        bind<ListData>().toInstance(exampleSharedIdentifier.getInstanceFromScope<ListData>())
        bind<DetailData>().toInstance(data)

        bind<IDetailRouter>().toClass(routerClass.kClass).singleton()
        bind<DetailPresenter>().toClass<DetailPresenter>().singleton()
    }
}