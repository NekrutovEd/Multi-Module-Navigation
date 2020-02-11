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

    final override fun Scope.openSubScopes(): Scope =
        openSubScope(createScopeName(NavigatorModule::class, individuality))
            .openSubScope(createScopeName(this@FragmentModule::class, individuality))

    companion object {
        inline fun <reified SM : FragmentModule> Any.injectScope(arguments: Bundle?) {
            this.injectScope<SM>(arguments?.getString(INDIVIDUALITY) ?: "")
        }
    }
}