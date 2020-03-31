package ruf.view.locationmap.sample

import ruf.view.multi_module_navigation.module.ScopeModule
import toothpick.config.Module

class ExampleApiModule : Module() {
    init {
        //bind
    }

    companion object {
        val scopeIdentifier = ScopeModule.ScopeIdentifier("ExampleApiModule")
    }
}