package ruf.view.locationmap.navigator

import android.support.annotation.AnimRes
import android.support.annotation.AnimatorRes

interface ICustomizationCommand {

    fun setCustomAnimations(@AnimatorRes @AnimRes enter: Int, @AnimatorRes @AnimRes exit: Int): ICustomizationCommand
}