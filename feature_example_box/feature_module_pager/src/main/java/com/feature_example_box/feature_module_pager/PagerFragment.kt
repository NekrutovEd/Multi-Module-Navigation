package com.feature_example_box.feature_module_pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.accioblogger.feature_example_box.feature_module_pager.R
import kotlinx.android.synthetic.main.fragment_pager.view.*
import ruf.view.core.BaseFragment
import ruf.view.core.IView
import ruf.view.multi_module_navigation.module.FragmentModule.Companion.injectScope
import ruf.view.multi_module_navigation.module.ScopeModule
import toothpick.ktp.delegate.inject

class PagerFragment : BaseFragment(), IView {

    override val presenter by inject<PagerPresenter>()

    private val scopeIdentifier by inject<ScopeModule.ScopeIdentifier>(PagerIdentifier::class)
    private val modulePagerAdapter by lazy {
        ModulePagerAdapter(
            scopeIdentifier,
            childFragmentManager
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectScope(arguments)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pager, container, false).apply {
            modulePagerAdapter.addModules(presenter.getPagerModules())
            (viewPager as ViewPager).adapter = modulePagerAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        modulePagerAdapter.clear()
    }
}
