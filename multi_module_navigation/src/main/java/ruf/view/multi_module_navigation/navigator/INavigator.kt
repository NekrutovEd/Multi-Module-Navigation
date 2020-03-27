package ruf.view.multi_module_navigation.navigator

interface INavigator : ICommanderNavigator, INavigatorLifeCycle {
    var counter: Int
}