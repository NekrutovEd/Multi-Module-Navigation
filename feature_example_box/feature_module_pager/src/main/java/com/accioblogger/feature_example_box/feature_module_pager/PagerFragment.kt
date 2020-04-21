package com.accioblogger.feature_example_box.feature_module_pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.fragment_pager.view.*
import ruf.view.core.BaseFragment
import ruf.view.core.IView
import ruf.view.multi_module_navigation.module.FragmentModule.Companion.injectScope
import ruf.view.multi_module_navigation.module.ScopeModule
import toothpick.ktp.delegate.inject

class PagerFragment : BaseFragment(), IView {

    override val presenter: PagerPresenter by inject()

    private val scopeIdentifier: ScopeModule.ScopeIdentifier by inject(PagerIdentifier::class)
    private val moduleStatePagerAdapter by lazy { ModuleStatePagerAdapter(scopeIdentifier, childFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectScope(arguments)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pager, container, false).apply {
            moduleStatePagerAdapter.addModules(presenter.getPagerModules())
            (viewPager as ViewPager).adapter = moduleStatePagerAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        moduleStatePagerAdapter.clear()
    }
}
