package ruf.view.mediator.pager

import com.accioblogger.feature_example_box.feature_container.NavigatorContainerModule
import com.accioblogger.feature_example_box.feature_module_pager.IPagerRouter
import ruf.view.feature_list_presentation.ListModule
import ruf.view.mediator.list.ListRouterClass
import ruf.view.multi_module_navigation.ParentNavigator
import ruf.view.multi_module_navigation.module.FragmentModule
import ruf.view.multi_module_navigation.navigator.ICommanderNavigator
import toothpick.InjectConstructor

@InjectConstructor
class PagerRouter(
    @ParentNavigator private val parentCommander: ICommanderNavigator
) : IPagerRouter {

    override fun getPagerModule(): List<FragmentModule> = listOf(
        NavigatorContainerModule(ListModule(ListRouterClass, "1 F")),
        NavigatorContainerModule(ListModule(ListRouterClass, "2 F")),
        ListModule(ListRouterClass, "3 F"),
        ListModule(ListRouterClass, "4 F")
    )
}
