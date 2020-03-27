package ruf.view.locationmap.library.module

import androidx.annotation.IdRes
import ruf.view.locationmap.library.navigator.INavigator
import toothpick.ktp.KTP
import toothpick.ktp.extension.getInstance
import java.util.*
import javax.inject.Provider

abstract class NavigatorProvider(
    @IdRes containerId: Int,
    startModule: FragmentModule?,
    private val scopeName: String = UUID.randomUUID().toString()
) : Provider<INavigator> {

    init {
        NavigatorModule(containerId, startModule, scopeName).installModule()
    }

    final override fun get() =
        KTP.openScope(ScopeModule.ScopeIdentifier(NavigatorModule::class, scopeName)).getInstance<INavigator>()
}