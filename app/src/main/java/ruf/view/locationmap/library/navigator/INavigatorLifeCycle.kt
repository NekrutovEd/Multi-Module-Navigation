package ruf.view.locationmap.library.navigator

import android.os.Bundle
import android.support.v4.app.FragmentManager
import ruf.view.locationmap.library.IOnBackPressed

interface INavigatorLifeCycle : IOnBackPressed {

    fun attachFragmentManager(fragmentManager: FragmentManager)

    fun detachFragmentManager()

    fun onSaveInstanceState(outState: Bundle)

    fun onViewStateRestored(savedInstanceState: Bundle?)
}