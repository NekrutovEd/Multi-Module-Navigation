package ruf.view.locationmap.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ruf.view.core.LogFragment
import ruf.view.locationmap.R
import ruf.view.mediator.launcher.MainLauncher
import ruf.view.multi_module_navigation.IOnBackPressed
import ruf.view.multi_module_navigation.module.NavigatorProvider
import ruf.view.multi_module_navigation.module.ScopeModule
import ruf.view.multi_module_navigation.navigator.INavigatorLifeCycle

class ContainerFragment : LogFragment(), IOnBackPressed {

    private val mainNavigator by lazy<INavigatorLifeCycle> { RootNavigatorProvider().get() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
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

    private class RootNavigatorProvider : NavigatorProvider(
        containerId = R.id.container,
        launcher = MainLauncher,
        scopeIdentifier = ScopeModule.ScopeIdentifier(UNIQUE_ROOT_SCOPE_NAME),
        parentScopeIdentifier = ExampleApiModule.scopeIdentifier
    )

    companion object {
        const val UNIQUE_ROOT_SCOPE_NAME = "UniqueRootScopeName"
    }
}