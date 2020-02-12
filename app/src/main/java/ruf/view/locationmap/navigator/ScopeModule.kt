package ruf.view.locationmap.navigator

import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.KTP

abstract class ScopeModule : Module() {

    abstract val scopeName: Any

    abstract fun Scope.openSubScopes(): Scope

    open fun onCloseScope() {}

    fun installModule() {
        KTP.openRootScope().openSubScopes().installModules(this)
    }

    fun isOpen() = KTP.isScopeOpen(ScopeIdentifier(this::class, scopeName))

    fun close() {
        KTP.closeScope(ScopeIdentifier(this::class, scopeName))
        onCloseScope()
    }

    companion object {
        inline fun <reified SM : ScopeModule> Any.injectScope(scopeName: Any) {
            KTP.openScopes(ScopeIdentifier(SM::class, scopeName)).inject(this)
        }
    }
}