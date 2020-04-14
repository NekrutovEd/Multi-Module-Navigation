package ruf.view.multi_module_navigation.module

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.KTP
import toothpick.ktp.extension.getInstance
import java.util.*
import kotlin.reflect.KClass

abstract class ScopeModule : Module() {

    abstract val scopeIdentifier: ScopeIdentifier

    open var parentScopeIdentifier: ScopeIdentifier? = null

    fun installModule() {
        val parentScope = parentScopeIdentifier?.let { KTP.openScope(it) } ?: KTP.openRootScope()
        parentScope.openSubScopes().installModules(this)
    }

    private fun Scope.openSubScopes(): Scope = openDependentScopes().openSubScope(scopeIdentifier)

    protected open fun Scope.openDependentScopes(): Scope = this

    fun isOpen() = KTP.isScopeOpen(scopeIdentifier)

    fun close() {
        KTP.closeScope(scopeIdentifier)
        onCloseScope()
    }

    protected open fun onCloseScope() {}

    @Parcelize
    open class ScopeIdentifier(open val name: String) : Parcelable {
        constructor(moduleClass: KClass<out ScopeModule>, name: String) : this(name) {
            this.moduleClass = moduleClass
        }

        @IgnoredOnParcel
        protected var moduleClass: KClass<out ScopeModule>? = null

        inline fun <reified T> getInstanceFromScope(): T = KTP.openScope(this).getInstance()

        override fun toString(): String = "${this::class.simpleName}${moduleClass?.run { "<${simpleName}>" } ?: ""}($name)"

        override fun equals(other: Any?) = name == (other as? ScopeIdentifier)?.name

        override fun hashCode() = name.hashCode()
    }

    companion object {
        fun Any.injectScope(scopeIdentifier: ScopeIdentifier) = KTP.openScope(scopeIdentifier).inject(this)

        inline fun <reified SM : ScopeModule> randomScopeIdentifier() = ScopeIdentifier(SM::class, UUID.randomUUID().toString())
    }
}