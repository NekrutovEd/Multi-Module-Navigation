package ruf.view.locationmap.library.navigator

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ruf.view.locationmap.library.CustomizationCommand
import ruf.view.locationmap.library.IOnBackPressed
import ruf.view.locationmap.library.command.BackCommand
import ruf.view.locationmap.library.command.ForwardCommand
import ruf.view.locationmap.library.command.ICommand
import ruf.view.locationmap.library.command.ICommandExecutor
import ruf.view.locationmap.library.module.DialogFragmentModule
import ruf.view.locationmap.library.module.FragmentModule
import ruf.view.locationmap.library.module.INDIVIDUALITY
import java.util.*
import kotlin.reflect.KClass

class Navigator(
    @IdRes private val containerId: Int,
    startModule: FragmentModule?,
    override val navigatorScopeName: String
) : INavigator, INavigatorManager, ICommandExecutor {

    var counter = 0

    private var fragmentManager: FragmentManager? = null

    private val stack = Stack<FragmentModule>()

    private val buffer = Buffer()

    init {
        startModule?.also {
            execute(object : ICommand {
                override fun execute(navigatorManager: INavigatorManager) {
                    if (stack.isEmpty()) ForwardCommand { startModule }.execute(navigatorManager)
                }
            })
        }
    }

    override fun onBackPressed(): Boolean {
        val scopeName = stack.takeIf { it.isNotEmpty() }?.peek()?.scopeName
        val iOnBackPressed = fragmentManager?.findFragmentByTag(scopeName) as? IOnBackPressed
        return when {
            iOnBackPressed?.onBackPressed() == true -> true

            stack.size > 1 -> {
                execute(BackCommand())
                true
            }

            else -> false
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        counter = savedInstanceState?.getInt(INDIVIDUALITY + "COUNTER") ?: counter
        savedInstanceState?.getParcelableArrayList<FragmentModule>(INDIVIDUALITY + "LIST")?.let { savedList ->
            savedList.takeIf { it.isNotEmpty() }
                ?.also {
                    stack.clear()
                    stack.addAll(it)
                    stack.peek()?.also { module ->
                        module.navigatorScopeName = navigatorScopeName
                        module.installModule()
                    }
                }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(INDIVIDUALITY + "COUNTER", counter)
        outState.putParcelableArrayList(INDIVIDUALITY + "LIST", ArrayList(stack))
    }

    override fun attachFragmentManager(fragmentManager: FragmentManager) {
        this.fragmentManager = fragmentManager
        stack.takeIf { it.isNotEmpty() }?.peek()?.also { module ->
            fragmentManager.findFragmentByTag(module.scopeName)
                ?: fragmentManager.commit {
                    replace(containerId, module.createFragment(), module.scopeName)
                    addToBackStack(module.scopeName)
                }
        }
        buffer.executeAll(this)
    }

    override fun detachFragmentManager() {
        fragmentManager = null
    }

    override fun execute(command: ICommand) {
        if (fragmentManager != null) {
            command.execute(this)
        } else {
            buffer.add(command)
        }
    }

    override fun replace(module: FragmentModule, customization: CustomizationCommand?) {
        (module as? DialogFragmentModule)?.also { add(it, customization) }
            ?: fragmentManager?.findFragmentByTag(module.scopeName)
            ?: fragmentManager?.commit {
                customizeTransaction(customization)
                replace(containerId, module.createFragment(), module.scopeName)
                stack.push(module)
                addToBackStack(module.scopeName)
            }
    }

    override fun add(module: FragmentModule, customization: CustomizationCommand?) {
        fragmentManager?.findFragmentByTag(module.scopeName)
            ?: fragmentManager?.commit {
                customizeTransaction(customization)
                add(module.createFragment(), module.scopeName)
                stack.push(module)
                addToBackStack(module.scopeName)
            }
    }

    override fun showDialog(module: DialogFragmentModule, customization: CustomizationCommand?) {
        fragmentManager?.findFragmentByTag(module.scopeName)
            ?: fragmentManager?.commit {
                customizeTransaction(customization)
                add(module.createFragment(), module.scopeName)
            }
    }

    override fun popBackStack() {
        if (stack.isNotEmpty()) {
            fragmentManager?.popBackStack()
            stack.popAndClose()
        }
    }

    override fun popBackStackTo(kClass: KClass<out FragmentModule>?) {
        if (kClass == null) {
            fragmentManager?.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            stack.forEach { it.close() }
            stack.clear()
        } else if (stack.isNotEmpty()) {
            val fragmentModule = stack.findLast { it::class == kClass }
            val distance = stack.search(fragmentModule)
            if (distance <= 1) return
            fragmentManager?.popBackStackImmediate(fragmentModule?.scopeName, 0)
            for (i in 1 until distance) {
                stack.popAndClose()
            }
        }
    }
}

private fun Stack<FragmentModule>.popAndClose() = pop().close()

private fun FragmentTransaction.customizeTransaction(cc: CustomizationCommand?): FragmentTransaction {
    cc?.customize(this)
    return this
}

private fun FragmentManager.commit(transaction: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().transaction().commit()