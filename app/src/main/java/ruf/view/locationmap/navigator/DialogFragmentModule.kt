package ruf.view.locationmap.navigator

import android.support.v4.app.DialogFragment
import kotlin.reflect.KClass

abstract class DialogFragmentModule(fragment: KClass<out DialogFragment>) : FragmentModule(fragment)