package com.common.toolbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_toolbar.*
import kotlinx.android.synthetic.main.fragment_toolbar.view.*
import ruf.view.core.BaseFragment
import ruf.view.core.IPresenter
import ruf.view.core.IView
import ruf.view.multi_module_navigation.IOnBackPressed
import ruf.view.multi_module_navigation.module.FragmentModule
import ruf.view.multi_module_navigation.module.FragmentModule.Companion.injectScope
import toothpick.ktp.delegate.inject

internal class ToolbarFragment : BaseFragment(), IOnBackPressed {

    override val presenter = object : IPresenter {
        override fun attachView(view: IView) {}
        override fun detachView() {}
    }

    private val navigationItem by inject<ToolbarNavigationItem>()
    private val items by inject<Array<out ToolbarItem>>()
    private val launchModule by inject<FragmentModule>()

    override fun onCreate(savedInstanceState: Bundle?) {
        injectScope(arguments)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_toolbar, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigationItem.takeIf { it != ToolbarNavigationItem.None }?.also { item ->
            view.toolbar.run {
                setNavigationIcon(item.iconId)
                setNavigationContentDescription(item.description)
                setNavigationOnClickListener { item.action(getFragmentFromContainer()) }
            }
        }

        items.forEach { item ->
            view.toolbar.menu.add(item.title)
                .setIcon(item.iconId)
                .setOnMenuItemClickListener { item.action(getFragmentFromContainer()) }
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        }

        getFragmentFromContainer()
            ?: childFragmentManager
                .beginTransaction()
                .replace(R.id.container, createLaunchFragment())
                .commit()
    }

    override fun onStart() {
        super.onStart()
        (childFragmentManager.findFragmentById(R.id.container) as? BaseFragment)?.title?.also { toolbar.title = it }
    }

    override fun onBackPressed() = (getFragmentFromContainer() as? IOnBackPressed)?.onBackPressed() ?: false

    private fun createLaunchFragment(): Fragment {
        launchModule.installModule()
        return launchModule.createFragment()
    }

    private fun getFragmentFromContainer() = childFragmentManager.findFragmentById(R.id.container)
}