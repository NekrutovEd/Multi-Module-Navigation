package ruf.view.locationmap.library.navigator

import android.os.Bundle
import android.support.v4.app.FragmentManager

interface INavigatorLifeCycle {

    fun attachFragmentManager(fragmentManager: FragmentManager)

    fun detachFragmentManager()

    fun onSaveInstanceState(outState: Bundle)

    fun onViewStateRestored(savedInstanceState: Bundle?)

    fun onBackPressed(): Boolean
}