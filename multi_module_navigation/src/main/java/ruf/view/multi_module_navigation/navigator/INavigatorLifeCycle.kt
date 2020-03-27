package ruf.view.multi_module_navigation.navigator

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import ruf.view.multi_module_navigation.IOnBackPressed

interface INavigatorLifeCycle : IOnBackPressed {

    fun attachFragmentManager(fragmentManager: FragmentManager)

    fun detachFragmentManager()

    fun onSaveInstanceState(outState: Bundle)

    fun onViewStateRestored(savedInstanceState: Bundle?)
}