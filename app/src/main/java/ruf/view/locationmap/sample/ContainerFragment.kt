package ruf.view.locationmap.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ruf.view.locationmap.R
import ruf.view.locationmap.navigator.Navigator
import ruf.view.locationmap.navigator.NavigatorModule
import ruf.view.locationmap.navigator.NavigatorModule.Companion.injectNavigator
import ruf.view.locationmap.sample.list.ListModule
import toothpick.ktp.delegate.inject

class ContainerFragment : Fragment() {

    private val mainNavigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NavigatorModule(R.id.container, ContainerFragment::class).installModule()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.container_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectNavigator(ContainerFragment::class)
        mainNavigator.forwardIfEmpty(ListModule())
        mainNavigator.attachFragmentManager(childFragmentManager)
    }

    override fun onDestroyView() {
        mainNavigator.detachFragmentManager()
        super.onDestroyView()
    }
}