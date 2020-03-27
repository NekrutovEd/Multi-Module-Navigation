package ruf.view.multi_module_navigation.module

import androidx.annotation.IdRes
import ruf.view.multi_module_navigation.ParentNavigator
import ruf.view.multi_module_navigation.navigator.INavigator
import ruf.view.multi_module_navigation.navigator.INavigatorCommand
import ruf.view.multi_module_navigation.navigator.Navigator
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