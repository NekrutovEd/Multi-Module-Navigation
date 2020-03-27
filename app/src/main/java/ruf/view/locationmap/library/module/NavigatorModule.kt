package ruf.view.locationmap.library.module

import androidx.annotation.IdRes
import ruf.view.locationmap.library.navigator.INavigator
import ruf.view.locationmap.library.navigator.INavigatorCommand
import ruf.view.locationmap.library.navigator.Navigator
import ruf.view.locationmap.sample.ParentNavigator
import toothpick.Scope
import toothpick.ktp.binding.bind

class NavigatorModule(
    @IdRes containerId: Int,
    startModule: FragmentModule?,
    override val scopeName: String
) : ScopeModule() {

    init {
        val navigator = Navigator(containerId, startModule, scopeName)
        bind<INavigator>().toInstance(navigator)
        bind<INavigatorCommand>().withName(ParentNavigator::class).toInstance(navigator)
    }

    override fun Scope.openSubScopes(): Scope = openSubScope(ScopeIdentifier(NavigatorModule::class, scopeName))
}