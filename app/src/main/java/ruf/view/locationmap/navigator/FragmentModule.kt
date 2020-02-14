package ruf.view.locationmap.navigator

import android.os.Bundle
import android.support.v4.app.Fragment
import toothpick.Scope
import java.util.*
import kotlin.reflect.KClass

const val INDIVIDUALITY = "INDIVIDUALITY"

abstract class FragmentModule(private val fragment: Class<out Fragment>) : ScopeModule() {

    constructor(fragment: KClass<out Fragment>) : this(fragment.java)

    override val scopeName = UUID.randomUUID().toString()

    var navigatorScopeName: Any = Any()

    fun createFragment(): Fragment = fragment.newInstance().also {
        val arguments = it.arguments ?: Bundle()
        arguments.putString(INDIVIDUALITY, scopeName)
        it.arguments = arguments
    }

    protected open fun Scope.openDependentScopes(): Scope = this

    final override fun Scope.openSubScopes(): Scope {
        return openSubScope(ScopeIdentifier(NavigatorModule::class, navigatorScopeName))
            .openDependentScopes()
            .openSubScope(ScopeIdentifier(this@FragmentModule::class, scopeName))
    }

    companion object {
        inline fun <reified SM : FragmentModule> Fragment.injectScope(arguments: Bundle?) {
            this.injectScope<SM>(arguments?.getString(INDIVIDUALITY) ?: "")
        }
    }
}