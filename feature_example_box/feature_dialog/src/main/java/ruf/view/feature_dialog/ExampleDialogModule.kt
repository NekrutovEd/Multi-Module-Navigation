package ruf.view.feature_dialog

import kotlinx.android.parcel.Parcelize
import ruf.view.multi_module_navigation.module.DialogFragmentModule

@Parcelize
class ExampleDialogModule(override val scopeIdentifier: ScopeIdentifier = randomScopeIdentifier<ExampleDialogModule>()) :
    DialogFragmentModule(ExampleDialogFragment::class)