package ruf.view.mediator.router.pager

import com.common.toolbar.ToolbarItem
import com.common.toolbar.ToolbarNavigationItem
import com.common.toolbar.toolbar
import com.feature_example_box.feature_container.navContainer
import com.feature_example_box.feature_module_pager.IPagerRouter
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
        navContainer {
            ListModule(ListRouter.Class, "1 F")
        },
        navContainer {
            toolbar(navigationItem = ToolbarNavigationItem.Back) {
                ListModule(ListRouter.Class, "2 F")
            }
        },
        toolbar {
            navContainer {
                ListModule(ListRouter.Class, "3 F")
            }
        },
        toolbar(ToolbarItem.Add) {
            ListModule(ListRouter.Class, "4 F")
        },
        ListModule(ListRouter.Class, "5 F")
    )

    @Parcelize
    object Class : IRouterClass<PagerRouter> {
        override val kClass get() = PagerRouter::class
    }
}
