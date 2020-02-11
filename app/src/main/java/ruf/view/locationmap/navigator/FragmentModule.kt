package ruf.view.locationmap.navigator

import android.os.Bundle
import android.support.v4.app.Fragment
import toothpick.Scope

const val INDIVIDUALITY = "INDIVIDUALITY"

abstract class FragmentModule : ScopeModule() {

    val fragment: Fragment
        get() = createFragment().also {
            val arguments = it.arguments ?: Bundle()
            arguments.putString(INDIVIDUALITY, individuality)
            it.arguments = arguments
        }


    protected abstract fun createFragment(): Fragment

    protected open fun Scope.openDependentScopes(): Scope = this

    final override fun Scope.openSubScopes(): Scope {
        return openSubScope<NavigatorModule>()
            .openDependentScopes()
            .openSubScope(createScopeName(this@FragmentModule::class, individuality))
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