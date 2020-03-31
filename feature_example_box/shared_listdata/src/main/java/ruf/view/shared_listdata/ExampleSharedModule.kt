package ruf.view.shared_listdata

import kotlinx.android.parcel.Parcelize
import ruf.view.multi_module_navigation.module.SharedModule
import toothpick.ktp.binding.bind

class ExampleSharedModule(
    textData: String,
    scopeName: String
) : SharedModule() {

    override val scopeIdentifier = ExampleSharedIdentifier(scopeName)

    init {
        bind<ListData>().toInstance(ListData(textData))
        installModule()
    }

    @Parcelize
    data class ExampleSharedIdentifier(override val name: String) : ScopeIdentifier(name)
}