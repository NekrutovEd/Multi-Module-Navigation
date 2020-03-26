package ruf.view.locationmap.library.module

import toothpick.Scope

abstract class SharedModule : ScopeModule() {

    lateinit var navigatorScopeName: String

    protected open fun Scope.openDependentScopes(): Scope = this

    final override fun Scope.openSubScopes(): Scope {
        return openSubScope(ScopeIdentifier(NavigatorModule::class, navigatorScopeName))
            .openDependentScopes()
            .openSubScope(scopeName)
    }
}