package com.feature_example_box.feature_container

import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import ruf.view.multi_module_navigation.module.FragmentModule
import ruf.view.multi_module_navigation.navigator.ICommanderNavigator
import ruf.view.multi_module_navigation.navigator.INavigatorLifeCycle
import toothpick.ktp.binding.bind

@Parcelize
internal class NavigatorContainerModule(
    private val module: FragmentModule,
    override val scopeIdentifier: ScopeIdentifier = randomScopeIdentifier<NavigatorContainerModule>()
) : FragmentModule(NavigatorContainerFragment::class) {

    @IgnoredOnParcel
    private val provider =
        NavigatorContainerFragment.NavigatorContainerProvider(
            module,
            scopeIdentifier
        )

    init {
        bind<INavigatorLifeCycle>().withName(ContainerNavigator::class).toProviderInstance(provider)
        bind<ICommanderNavigator>().withName(ContainerNavigator::class).toProviderInstance(provider)
    }
}

fun navContainer(getFragmentModule: () -> FragmentModule): FragmentModule =
    NavigatorContainerModule(getFragmentModule())