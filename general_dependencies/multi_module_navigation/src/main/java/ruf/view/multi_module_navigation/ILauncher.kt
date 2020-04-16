package ruf.view.multi_module_navigation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ruf.view.multi_module_navigation.module.FragmentModule

interface ILauncher : Parcelable {

    val launchModule: FragmentModule
}

@Parcelize
class UniversalLauncher(override val launchModule: FragmentModule) : ILauncher
