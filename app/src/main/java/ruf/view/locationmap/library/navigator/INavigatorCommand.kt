package ruf.view.locationmap.library.navigator

import ruf.view.locationmap.library.ICustomizationCommand
import ruf.view.locationmap.library.command.*
import ruf.view.locationmap.library.module.DialogFragmentModule
import ruf.view.locationmap.library.module.FragmentModule
import kotlin.reflect.KClass

interface INavigatorCommand : ICommandExecutor {

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

inline fun <reified K : FragmentModule> INavigatorCommand.backTo() = backTo(K::class)