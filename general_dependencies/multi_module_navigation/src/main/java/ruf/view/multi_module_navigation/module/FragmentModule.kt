package ruf.view.multi_module_navigation.module

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlin.reflect.KClass

const val FRAGMENT_MODULE_KEY = "Scope_Identifier_Key_For_FragmentModule"

abstract class FragmentModule(private val fragment: Class<out Fragment>) : ScopeModule(), Parcelable {
    constructor(fragment: KClass<out Fragment>) : this(fragment.java)

    val scopeTag get() = scopeIdentifier.name

    open fun customizeTransactionsWithModule(transaction: FragmentTransaction): FragmentTransaction = transaction

    fun createFragment(): Fragment = fragment.newInstance().also {
        val arguments = it.arguments ?: Bundle()
        arguments.putParcelable(FRAGMENT_MODULE_KEY, scopeIdentifier)
        it.arguments = arguments
    }

    companion object {
        fun Fragment.injectScope(arguments: Bundle?) {
            val scopeIdentifier: ScopeIdentifier = arguments?.getParcelable(FRAGMENT_MODULE_KEY)
                ?: error("Arguments haven't ScopeIdentifier for FragmentModule")
            injectScope(scopeIdentifier)
        }
    }
}