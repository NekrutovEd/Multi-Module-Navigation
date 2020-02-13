package ruf.view.locationmap.navigator.list

import ruf.view.locationmap.navigator.FragmentModule
import toothpick.ktp.binding.bind

class ListModule : FragmentModule() {

    override fun createFragment() = ListFragment()

    init {
        bind<ListRouter>().toClass<ListRouter>().singleton()
        bind<ListPresenter>().toClass<ListPresenter>().singleton()
    }
}