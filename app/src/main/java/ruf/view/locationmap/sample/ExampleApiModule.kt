package ruf.view.locationmap.sample

import kotlinx.android.parcel.Parcelize
import ruf.view.multi_module_navigation.module.ScopeModule
import ruf.view.multi_module_navigation.module.SharedModule

class ExampleApiModule : SharedModule() {

    override val scopeIdentifier = Identifier

    init {
        //bind for api
    }

    @Parcelize
    object Identifier : ScopeModule.ScopeIdentifier("ExampleApiModule")
}
