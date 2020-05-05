package ruf.view.mediator.router.pager

import com.accioblogger.feature_example_box.feature_container.NavigatorContainerModule
import com.accioblogger.feature_example_box.feature_module_pager.IPagerRouter
import kotlinx.android.parcel.Parcelize
import ruf.view.core.IRouterClass
import ruf.view.feature_list_presentation.ListModule
import ruf.view.mediator.router.list.ListRouter
import ruf.view.multi_module_navigation.ParentNavigator
import ruf.view.multi_module_navigation.module.FragmentModule
import ruf.view.multi_module_navigation.navigator.ICommanderNavigator
import toothpick.InjectConstructor

@InjectConstructor
class PagerRouter(
    @ParentNavigator private val parentCommander: ICommanderNavigator
) : IPagerRouter {

    override fun getPagerModule(): List<FragmentModule> = listOf(
        NavigatorContainerModule(ListModule(ListRouter.Class, "1 F")),
        NavigatorContainerModule(ListModule(ListRouter.Class, "2 F")),
        ListModule(ListRouter.Class, "3 F"),
        ListModule(ListRouter.Class, "4 F")
    )

    @Parcelize
    object Class : IRouterClass<PagerRouter> {
        override val kClass get() = PagerRouter::class
    }
}
