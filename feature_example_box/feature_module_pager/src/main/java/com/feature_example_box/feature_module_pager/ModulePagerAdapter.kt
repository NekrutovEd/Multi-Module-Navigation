package com.feature_example_box.feature_module_pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ruf.view.multi_module_navigation.module.FragmentModule
import ruf.view.multi_module_navigation.module.ScopeModule

class ModulePagerAdapter(
    private val parentScopeIdentifier: ScopeModule.ScopeIdentifier,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val list = mutableListOf<Pair<FragmentModule, Fragment>>()

    fun addModules(modules: List<FragmentModule>) {
        list.addAll(modules.map {
            it.parentScopeIdentifier = parentScopeIdentifier
            it to it.createFragment()
        })
        notifyDataSetChanged()
    }

    fun addModule(module: FragmentModule) {
        module.parentScopeIdentifier = parentScopeIdentifier
        list.add(module to module.createFragment())
        notifyDataSetChanged()
    }

    fun removeModule(module: FragmentModule) {
        list.removeAll { it.first == module }
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        list[position].first.installModule()
        return list[position].second
    }

    override fun getCount(): Int = list.size
}
