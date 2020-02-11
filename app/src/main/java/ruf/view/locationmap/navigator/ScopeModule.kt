package ruf.view.locationmap.navigator

import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.KTP
import kotlin.reflect.KClass

abstract class ScopeModule : Module() {

    var individuality: String = ""

    abstract fun Scope.openSubScopes(): Scope

    fun installModule() {
        KTP.openRootScope().openSubScopes().installModules(this)
    }

    fun isOpen() = KTP.isScopeOpen(createScopeName(this::class, individuality))

    fun close() = KTP.closeScope(createScopeName(this::class, individuality))

    protected inline fun <reified M : ScopeModule> Scope.openSubScope(): Scope =
        openSubScope(createScopeName(M::class, individuality))

    companion object {
        inline fun <reified SM : ScopeModule> Any.injectScope(individuality: String) {
            KTP.openScopes(createScopeName(SM::class, individuality)).inject(this)
        }

        fun createScopeName(klass: KClass<out ScopeModule>, individuality: String): Any =
            klass.hashCode().toString() + individuality
    }
}