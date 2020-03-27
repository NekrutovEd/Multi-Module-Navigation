package ruf.view.feature_list_presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list.view.*
import ruf.view.core.IView
import ruf.view.core.LogFragment
import ruf.view.multi_module_navigation.IOnBackPressed
import ruf.view.multi_module_navigation.module.FragmentModule.Companion.injectScope
import ruf.view.multi_module_navigation.module.NavigatorProvider
import ruf.view.multi_module_navigation.navigator.INavigatorLifeCycle
import toothpick.InjectConstructor
import toothpick.ktp.delegate.inject

class ListFragment : LogFragment(), IView, IOnBackPressed {

    private val presenter: ListPresenter by inject()

    private val navigator: INavigatorLifeCycle by inject(ListNavigator::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        injectScope<ListModule>(arguments)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false).apply {
            val text = name.text.toString() + presenter.data.tag
            logTag += presenter.data.tag
            name.text = text
            open_detail.setOnClickListener { presenter.openDetail() }
            add_module.setOnClickListener { presenter.addModule() }
            remove_module.setOnClickListener { presenter.removeModule() }
            show_dialog.setOnClickListener { presenter.showDialog() }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
    }

    override fun onStart() {
        super.onStart()
        navigator.attachFragmentManager(childFragmentManager)
    }

    override fun onStop() {
        navigator.detachFragmentManager()
        super.onStop()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
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
    class ListNavigatorProvider : NavigatorProvider(R.id.child_container, null)
}