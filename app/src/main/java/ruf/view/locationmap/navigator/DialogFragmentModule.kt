package ruf.view.locationmap.navigator

import android.support.v4.app.DialogFragment

abstract class DialogFragmentModule : FragmentModule() {

    abstract override fun createFragment(): DialogFragment
}