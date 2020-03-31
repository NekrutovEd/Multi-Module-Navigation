package ruf.view.multi_module_navigation.module

import androidx.annotation.IdRes
import ruf.view.multi_module_navigation.ILauncher
import ruf.view.multi_module_navigation.ParentNavigator
import ruf.view.multi_module_navigation.navigator.ICommanderNavigator
import ruf.view.multi_module_navigation.navigator.INavigator
import ruf.view.multi_module_navigation.navigator.Navigator
import toothpick.ktp.binding.bind

class NavigatorModule(
    @IdRes containerId: Int,
    launcher: ILauncher?,
    override val scopeIdentifier: ScopeIdentifier
) : ScopeModule() {

    init {
        val navigator = Navigator(containerId, launcher, scopeIdentifier)
        bind<INavigator>().toInstance(navigator)
        bind<ICommanderNavigator>().withName(ParentNavigator::class).toInstance(navigator)
    }
}