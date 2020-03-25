package ruf.view.locationmap.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ruf.view.locationmap.R
import ruf.view.locationmap.library.IOnBackPressed
import ruf.view.locationmap.library.module.NavigatorProvider
import ruf.view.locationmap.library.navigator.INavigatorLifeCycle
import ruf.view.locationmap.sample.list.ListModule

class ContainerFragment : LogFragment(), IOnBackPressed {

    override var logTag: String = "ContainerFragment"

    private val mainNavigator: INavigatorLifeCycle by lazy { RootNavigatorProvider().get() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.container_fragment, container, false)

    override fun onStart() {
        super.onStart()
        mainNavigator.attachFragmentManager(childFragmentManager)
    }

    override fun onStop() {
        mainNavigator.detachFragmentManager()
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        mainNavigator.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        mainNavigator.onViewStateRestored(savedInstanceState)
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onBackPressed() = mainNavigator.onBackPressed()

    private class RootNavigatorProvider : NavigatorProvider(R.id.container, ListModule(" F"), "UniqueRootScopeName")
}