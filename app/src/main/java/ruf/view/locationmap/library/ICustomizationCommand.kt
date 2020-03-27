package ruf.view.locationmap.library

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes

interface ICustomizationCommand {

    fun setCustomAnimations(
        @AnimatorRes @AnimRes enter: Int,
        @AnimatorRes @AnimRes exit: Int
    ): ICustomizationCommand

    fun setCustomAnimations(
        @AnimatorRes @AnimRes enter: Int,
        @AnimatorRes @AnimRes exit: Int,
        @AnimatorRes @AnimRes popEnter: Int,
        @AnimatorRes @AnimRes popExit: Int
    ): ICustomizationCommand
}