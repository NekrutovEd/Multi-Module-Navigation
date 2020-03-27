package ruf.view.multi_module_navigation.navigator

interface INavigator : INavigatorCommand, INavigatorLifeCycle {
    var counter: Int
}