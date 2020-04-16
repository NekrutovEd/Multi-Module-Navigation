package ruf.view.mediator.launcher

import kotlinx.android.parcel.Parcelize
import ruf.view.feature_list_presentation.ListModule
import ruf.view.mediator.list.ListRouterClass
import ruf.view.multi_module_navigation.ILauncher
import ruf.view.multi_module_navigation.module.FragmentModule

@Parcelize
object MainLauncher : ILauncher {

    override val launchModule: FragmentModule get() = ListModule(ListRouterClass, " F")
}