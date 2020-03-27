package ruf.view.locationmap.library.module

import androidx.fragment.app.DialogFragment
import kotlin.reflect.KClass

abstract class DialogFragmentModule(fragment: Class<out DialogFragment>) : FragmentModule(fragment) {

    constructor(fragment: KClass<out DialogFragment>) : this(fragment.java)
}