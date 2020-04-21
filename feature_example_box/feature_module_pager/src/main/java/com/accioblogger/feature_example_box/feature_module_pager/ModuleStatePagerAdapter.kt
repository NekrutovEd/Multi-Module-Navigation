package com.accioblogger.feature_example_box.feature_module_pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ruf.view.multi_module_navigation.module.FragmentModule
import ruf.view.multi_module_navigation.module.ScopeModule

class ModuleStatePagerAdapter(
    private val parentScopeIdentifier: ScopeModule.ScopeIdentifier,
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val list: MutableList<Pair<FragmentModule, Fragment>> = mutableListOf()

    fun addModules(modules: List<FragmentModule>) {
        list.addAll(modules.map { it to it.createFragment() })
        notifyDataSetChanged()
    }

    fun addModule(module: FragmentModule) {
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
        val fragmentModule = list[position].first
        fragmentModule.parentScopeIdentifier = parentScopeIdentifier
        fragmentModule.installModule()
        return list[position].second
    }

    override fun getCount(): Int = list.size
}
