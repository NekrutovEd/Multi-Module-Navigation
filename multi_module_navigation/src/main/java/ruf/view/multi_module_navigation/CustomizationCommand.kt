package ruf.view.multi_module_navigation

import androidx.fragment.app.FragmentTransaction
import java.util.*

class CustomizationCommand : ICustomizationCommand {

    private val customList = LinkedList<FragmentTransaction.() -> Unit>()

    fun customize(fragmentTransaction: FragmentTransaction) = customList.forEach { it(fragmentTransaction) }

    override fun setCustomAnimations(enter: Int, exit: Int): ICustomizationCommand {
        customList.add { setCustomAnimations(enter, exit) }
        return this
    }

    override fun setCustomAnimations(enter: Int, exit: Int, popEnter: Int, popExit: Int): ICustomizationCommand {
        customList.add { setCustomAnimations(enter, exit, popEnter, popExit) }
        return this
    }
}