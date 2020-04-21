package ruf.view.multi_module_navigation.module

abstract class SharedModule : ScopeModule() {

    final override var parentScopeIdentifier: ScopeIdentifier
        get() = EmptyScopeIdentifier
        set(_) {}
}