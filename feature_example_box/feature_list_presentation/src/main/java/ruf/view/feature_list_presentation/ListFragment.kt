package ruf.view.feature_list_presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ruf.view.core.actions.IAddItemListener
import kotlinx.android.synthetic.main.fragment_list.view.*
import ruf.view.core.BaseFragment
import ruf.view.multi_module_navigation.IOnBackPressed
import ruf.view.multi_module_navigation.module.FragmentModule.Companion.injectScope
import ruf.view.multi_module_navigation.module.NavigatorProvider
import ruf.view.multi_module_navigation.navigator.INavigatorLifeCycle
import toothpick.InjectConstructor
import toothpick.ktp.delegate.inject

internal class ListFragment : BaseFragment(), IOnBackPressed, IAddItemListener {

    override val presenter by inject<ListPresenter>()

    override var title = "ListFragment"

    private val navigator by inject<INavigatorLifeCycle>(ListNavigator::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        injectScope(arguments)
        logTag += presenter.data.textData
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false).apply {
            val text = name.text.toString() + presenter.data.textData
            name.text = text
            open_detail.setOnClickListener { presenter.openDetail() }
            add_module.setOnClickListener { presenter.addModule() }
            remove_module.setOnClickListener { presenter.removeModule() }
            show_dialog.setOnClickListener { presenter.showDialog() }
        }
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

    override fun onClickItemAdd() = presenter.addModule()

    @InjectConstructor
    class ListNavigatorProvider : NavigatorProvider(
        containerId = R.id.child_container,
        launcher = null
    )
}