package ruf.view.feature_list_presentation

import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import ruf.view.core.IRouterClass
import ruf.view.multi_module_navigation.module.FragmentModule
import ruf.view.multi_module_navigation.navigator.ICommanderNavigator
import ruf.view.multi_module_navigation.navigator.INavigatorLifeCycle
import ruf.view.shared_listdata.ExampleSharedModule
import ruf.view.shared_listdata.ListData
import toothpick.ktp.binding.bind

@Parcelize
data class ListModule (
    private val routerClass: IRouterClass<IListRouter>,
    private val textData: String,
    override val scopeIdentifier: ScopeIdentifier = randomScopeIdentifier<ListModule>()
) : FragmentModule(ListFragment::class) {

    @IgnoredOnParcel
    private val sharedModule = ExampleSharedModule(textData, "ShareModule $scopeIdentifier")

    init {
        bind<ExampleSharedModule.ExampleSharedIdentifier>().toInstance(sharedModule.scopeIdentifier)
        bind<ListData>().toInstance(sharedModule.scopeIdentifier.getInstanceFromScope<ListData>())

        val provider = ListFragment.ListNavigatorProvider()
        bind<INavigatorLifeCycle>().withName(ListNavigator::class).toProviderInstance(provider)
        bind<ICommanderNavigator>().withName(ListNavigator::class).toProviderInstance(provider)

        bind<IListRouter>().toClass(routerClass.kClass).singleton()
        bind<ListPresenter>().toClass<ListPresenter>().singleton()
    }

    override fun onCloseScope() = sharedModule.close()
}
