package ruf.view.multi_module_navigation.navigator

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ruf.view.multi_module_navigation.*
import ruf.view.multi_module_navigation.command.BackCommand
import ruf.view.multi_module_navigation.command.ForwardCommand
import ruf.view.multi_module_navigation.command.ICommand
import ruf.view.multi_module_navigation.command.ICommandExecutor
import ruf.view.multi_module_navigation.module.DialogFragmentModule
import ruf.view.multi_module_navigation.module.FragmentModule
import ruf.view.multi_module_navigation.module.ScopeModule
import java.util.*
import kotlin.reflect.KClass

private const val NAVIGATOR_SAVE_INSTANCE_STATE_KEY = "Saved_Instance_State_Key_For_Navigator"

internal class Navigator(
    @IdRes private val containerId: Int,
    launcher: ILauncher?,
    override val navigatorScopeIdentifier: ScopeModule.ScopeIdentifier
) : INavigator, INavigatorManager, ICommandExecutor {

    override var counter = 0

    private var fragmentManager: FragmentManager? = null

    private val stack = Stack<FragmentModule>()

    private val buffer = Buffer()

    init {
        launcher?.also {
            execute(object : ICommand {
                override fun execute(navigatorManager: INavigatorManager) {
                    if (stack.isEmpty()) ForwardCommand { launcher.launchModule }.execute(navigatorManager)
                }
            })
        }
    }

    override fun onBackPressed(): Boolean {
        val scopeTag = stack.takeIf { it.isNotEmpty() }?.peek()?.scopeTag
        val iOnBackPressed = fragmentManager?.findFragmentByTag(scopeTag) as? IOnBackPressed
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
        counter = savedInstanceState?.getInt(NAVIGATOR_SAVE_INSTANCE_STATE_KEY + "_COUNTER") ?: counter
        savedInstanceState?.getParcelableArrayList<FragmentModule>(NAVIGATOR_SAVE_INSTANCE_STATE_KEY)?.let { savedList ->
            savedList.takeIf { it.isNotEmpty() }
                ?.also {
                    stack.clear()
                    stack.addAll(it)
                    stack.peek()?.also { module ->
                        module.parentScopeIdentifier = navigatorScopeIdentifier
                        module.installModule()
                    }
                }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(NAVIGATOR_SAVE_INSTANCE_STATE_KEY + "_COUNTER", counter)
        outState.putParcelableArrayList(NAVIGATOR_SAVE_INSTANCE_STATE_KEY, ArrayList(stack))
    }

    override fun attachFragmentManager(fragmentManager: FragmentManager) {
        this.fragmentManager = fragmentManager
        stack.takeIf { it.isNotEmpty() }?.peek()?.also { module ->
            fragmentManager.findFragmentByTag(module.scopeTag)
                ?: fragmentManager.commit {
                    replace(containerId, module.createFragment(), module.scopeTag)
                    addToBackStack(module.scopeTag)
                }
        }
        buffer.executeAll(this)
    }

    override fun detachFragmentManager() {
        fragmentManager = null
    }

    override fun closeAllModules() = stack.forEach { it.close() }

    override fun execute(command: ICommand) {
        if (fragmentManager != null) {
            command.execute(this)
        } else {
            buffer.add(command)
        }
    }

    override fun replace(module: FragmentModule, customizer: ICustomizer?) {
        (module as? DialogFragmentModule)?.also { add(it, customizer) }
            ?: fragmentManager?.findFragmentByTag(module.scopeTag)
            ?: fragmentManager?.commit {
                customizeTransaction(customizer)
                replace(containerId, module.createFragment(), module.scopeTag)
                stack.push(module)
                addToBackStack(module.scopeTag)
            }
    }

    override fun add(module: FragmentModule, customizer: ICustomizer?) {
        fragmentManager?.findFragmentByTag(module.scopeTag)
            ?: fragmentManager?.commit {
                customizeTransaction(customizer)
                add(module.createFragment(), module.scopeTag)
                stack.push(module)
                addToBackStack(module.scopeTag)
            }
    }

    override fun showDialog(module: DialogFragmentModule, customizer: ICustomizer?) {
        fragmentManager?.findFragmentByTag(module.scopeTag)
            ?: fragmentManager?.commit {
                customizeTransaction(customizer)
                add(module.createFragment(), module.scopeTag)
            }
    }

    override fun popBackStack() {
        if (stack.isNotEmpty()) {
            fragmentManager?.popBackStack()
            stack.popAndClose()
        }
    }

    override fun popBackStackTo(kClass: KClass<out FragmentModule>?): Int {
        return when {
            kClass == null -> {
                val sizeStack = stack.size
                fragmentManager?.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                stack.forEach { it.close() }
                stack.clear()
                sizeStack
            }

            stack.isNotEmpty() -> {
                val fragmentModule = stack.findLast { it::class == kClass }
                val distance = stack.search(fragmentModule) - 1
                if (distance > 0) {
                    fragmentManager?.popBackStackImmediate(fragmentModule?.scopeTag, 0)
                    for (i in 0 until distance) {
                        stack.popAndClose()
                    }
                }
                distance
            }

            else -> -1
        }
    }
}

private fun Stack<FragmentModule>.popAndClose() = pop().close()

private fun FragmentTransaction.customizeTransaction(customizer: ICustomizer?): FragmentTransaction {
    customizer?.customize(this)
    return this
}

private fun FragmentManager.commit(transaction: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().transaction().commit()