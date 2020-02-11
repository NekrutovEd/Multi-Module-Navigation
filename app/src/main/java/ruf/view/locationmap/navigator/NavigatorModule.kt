package ruf.view.locationmap.navigator

import android.support.annotation.IdRes
import android.support.v4.app.FragmentManager
import toothpick.Scope
import toothpick.ktp.binding.bind

class NavigatorModule(@IdRes private val containerId: Int, fragmentManager: FragmentManager) : ScopeModule() {

    val navigator = Navigator(containerId, fragmentManager)

    init {
        individuality = containerId.toString()

        bind<Navigator>().toInstance(navigator)
    }

    override fun Scope.openSubScopes(): Scope = openSubScope(createScopeName(NavigatorModule::class, individuality))

    companion object {
        fun Any.injectNavigator(@IdRes containerId: Int) {
            injectScope<NavigatorModule>(containerId.toString())
        }
    }
}