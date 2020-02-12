package ruf.view.locationmap.navigator

import android.os.Bundle
import android.support.v4.app.Fragment
import toothpick.Scope
import java.util.*

const val INDIVIDUALITY = "INDIVIDUALITY"

abstract class FragmentModule : ScopeModule() {

    override val scopeName = UUID.randomUUID().toString()

    var navigatorScopeName: Any = Any()

    val fragment: Fragment
        get() = createFragment().also {
            val arguments = it.arguments ?: Bundle()
            arguments.putString(INDIVIDUALITY, scopeName)
            it.arguments = arguments
        }


    protected abstract fun createFragment(): Fragment

    protected open fun Scope.openDependentScopes(): Scope = this

    final override fun Scope.openSubScopes(): Scope {
        return openSubScope(ScopeIdentifier(NavigatorModule::class, navigatorScopeName))
            .openDependentScopes()
            .openSubScope(ScopeIdentifier(this@FragmentModule::class, scopeName))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode() = javaClass.hashCode()

    companion object {
        inline fun <reified SM : FragmentModule> Any.injectScope(arguments: Bundle?) {
            this.injectScope<SM>(arguments?.getString(INDIVIDUALITY) ?: "")
        }
    }
}