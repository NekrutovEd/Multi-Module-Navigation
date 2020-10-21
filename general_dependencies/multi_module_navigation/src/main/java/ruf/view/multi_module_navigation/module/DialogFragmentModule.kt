package ruf.view.multi_module_navigation.module

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import toothpick.ktp.KTP
import kotlin.reflect.KClass

abstract class DialogFragmentModule(fragment: Class<out DialogFragment>) : FragmentModule(fragment) {

    constructor(fragment: KClass<out DialogFragment>) : this(fragment.java)

    companion object {

        fun DialogFragment.injectDialogScope(savedInstanceState: Bundle?) {
            if (savedInstanceState == null) injectScope() else dismiss()
        }

        fun DialogFragment.dismissDialogScope() {
            val scopeIdentifier: ScopeIdentifier = arguments?.getParcelable(FRAGMENT_MODULE_KEY)
                ?: error("Arguments haven't ScopeIdentifier for FragmentModule")
            KTP.closeScope(scopeIdentifier)
        }
    }
}
