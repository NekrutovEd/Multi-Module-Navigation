package ruf.view.multi_module_navigation.navigator

import ruf.view.multi_module_navigation.ICustomizationCommand
import ruf.view.multi_module_navigation.command.*
import ruf.view.multi_module_navigation.module.DialogFragmentModule
import ruf.view.multi_module_navigation.module.FragmentModule
import kotlin.reflect.KClass

interface ICommanderNavigator : ICommandExecutor {

    fun forward(getModule: ICustomizationCommand.() -> FragmentModule) =
        execute(ForwardCommand(getModule))

    fun replace(getModule: ICustomizationCommand.() -> FragmentModule) =
        execute(ReplaceCommand(getModule))

    fun back() = execute(BackCommand())

    /** @return | -1 = stack.empty | n = count step back | */
    fun backTo(kClass: KClass<out FragmentModule>?) =
        execute(BackToCommand(kClass))

    fun showDialog(getDialogModule: ICustomizationCommand.() -> DialogFragmentModule) =
        execute(ShowDialogCommand(getDialogModule))
}

inline fun <reified K : FragmentModule> ICommanderNavigator.backTo() = backTo(K::class)