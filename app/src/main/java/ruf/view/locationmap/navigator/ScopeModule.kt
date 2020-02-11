package ruf.view.locationmap.navigator

import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.KTP
import kotlin.reflect.KClass

abstract class ScopeModule : Module() {

    var individuality: String = ""

    abstract fun Scope.openSubScopes(): Scope

    fun installModule() {
        if (isOpen()) close()
        KTP.openRootScope().openSubScopes().installModules(this)
    }

    fun isOpen() = KTP.isScopeOpen(createScopeName(this::class, individuality))

    fun close() = KTP.closeScope(createScopeName(this::class, individuality))

    companion object {
        inline fun <reified SM : ScopeModule> Any.injectScope(individuality: String) {
            KTP.openScopes(createScopeName(SM::class, individuality)).inject(this)
        }

        fun createScopeName(klass: KClass<out ScopeModule>, individuality: String): Any =
            klass.hashCode().toString() + individuality
    }
}