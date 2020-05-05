package com.common.toolbar

import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import ruf.view.multi_module_navigation.module.FragmentModule
import toothpick.ktp.binding.bind
import kotlin.reflect.KClass

@Suppress("CanBeParameter")
@Parcelize
internal class ToolbarModule(
    private val launchModule: FragmentModule,
    private vararg val items: ToolbarItem,
    private val navigationItem: ToolbarNavigationItem = ToolbarNavigationItem.None,
    override val scopeIdentifier: ScopeIdentifier = randomScopeIdentifier<ToolbarModule>()
) : FragmentModule(ToolbarFragment::class) {

    @IgnoredOnParcel
    override var parentScopeIdentifier: ScopeIdentifier = EmptyScopeIdentifier
        set(value) {
            launchModule.parentScopeIdentifier = value
            field = value
        }

    init {
        bind<ToolbarNavigationItem>().toInstance(navigationItem)
        bind<Array<out ToolbarItem>>().toInstance(items)
        bind<FragmentModule>().toInstance(launchModule)
    }

    override fun containsModule(kClass: KClass<out FragmentModule>) = launchModule.containsModule(kClass)
}

fun toolbar(
    vararg items: ToolbarItem,
    navigationItem: ToolbarNavigationItem = ToolbarNavigationItem.None,
    getFragmentModule: () -> FragmentModule
): FragmentModule = ToolbarModule(getFragmentModule(), items = *items, navigationItem = navigationItem)
