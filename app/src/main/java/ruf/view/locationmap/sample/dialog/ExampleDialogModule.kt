package ruf.view.locationmap.sample.dialog

import kotlinx.android.parcel.Parcelize
import ruf.view.multi_module_navigation.module.DialogFragmentModule
import java.util.*

@Parcelize
class ExampleDialogModule(override val scopeName: String = UUID.randomUUID().toString()) :
    DialogFragmentModule(ExampleDialogFragment::class)