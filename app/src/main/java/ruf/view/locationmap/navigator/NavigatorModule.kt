package ruf.view.locationmap.navigator

import android.support.annotation.IdRes
import toothpick.Scope
import toothpick.ktp.binding.bind

class NavigatorModule(@IdRes val containerId: Int, navigatorScopeName: Any = Any()) : ScopeModule() {

    val navigator = Navigator(containerId, navigatorScopeName)

    override val scopeName
        get() = navigator.navigatorScopeName

    init {
        bind<Navigator>().toInstance(navigator)
        bind<INavigatorCommand>().toInstance(navigator)
    }

    override fun Scope.openSubScopes(): Scope = openSubScope(ScopeIdentifier(NavigatorModule::class, scopeName))

    override fun onCloseScope() {
        navigator.destroy()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NavigatorModule

        if (containerId != other.containerId) return false

        return true
    }

    override fun hashCode() = containerId

    companion object {
        fun Any.injectNavigator(scopeName: Any) {
            injectScope<NavigatorModule>(scopeName)
        }
    }
}