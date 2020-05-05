package com.feature_example_box.feature_container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.accioblogger.feature_example_box.feature_container.R
import ruf.view.multi_module_navigation.IOnBackPressed
import ruf.view.multi_module_navigation.UniversalLauncher
import ruf.view.multi_module_navigation.module.FragmentModule
import ruf.view.multi_module_navigation.module.FragmentModule.Companion.injectScope
import ruf.view.multi_module_navigation.module.NavigatorProvider
import ruf.view.multi_module_navigation.module.ScopeModule
import ruf.view.multi_module_navigation.navigator.INavigatorLifeCycle
import toothpick.InjectConstructor
import toothpick.ktp.delegate.inject

class NavigatorContainerFragment : Fragment(), IOnBackPressed {

    private val navigator by inject<INavigatorLifeCycle>(ContainerNavigator::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        injectScope(arguments)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    override fun onStart() {
        super.onStart()
        navigator.attachFragmentManager(childFragmentManager)
    }

    override fun onStop() {
        navigator.detachFragmentManager()
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        navigator.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        navigator.onViewStateRestored(savedInstanceState)
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onBackPressed() = navigator.onBackPressed()

    @InjectConstructor
    class NavigatorContainerProvider(module: FragmentModule, scopeIdentifier: ScopeModule.ScopeIdentifier) : NavigatorProvider(
        containerId = R.id.container,
        launcher = UniversalLauncher(module),
        parentScopeIdentifier = scopeIdentifier
    )
}
