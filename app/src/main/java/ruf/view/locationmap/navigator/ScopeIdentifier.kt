package ruf.view.locationmap.navigator

import kotlin.reflect.KClass

data class ScopeIdentifier(
    private val moduleClass: KClass<out ScopeModule>,
    private val scopeName: Any
)