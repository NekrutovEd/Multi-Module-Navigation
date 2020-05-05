package ruf.view.mediator.launcher

import com.accioblogger.feature_example_box.feature_module_pager.PagerModule
import kotlinx.android.parcel.Parcelize
import ruf.view.mediator.router.pager.PagerRouter
import ruf.view.multi_module_navigation.ILauncher
import ruf.view.multi_module_navigation.module.FragmentModule

@Parcelize
object MainLauncher : ILauncher {

    override val launchModule: FragmentModule get() = PagerModule(PagerRouter.Class)
}
