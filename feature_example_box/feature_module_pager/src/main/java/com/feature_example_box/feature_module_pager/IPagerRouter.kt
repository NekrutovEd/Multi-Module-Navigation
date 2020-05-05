package com.feature_example_box.feature_module_pager

import ruf.view.core.IRouter
import ruf.view.multi_module_navigation.module.FragmentModule

interface IPagerRouter : IRouter {

    fun getPagerModule() : List<FragmentModule>
}
