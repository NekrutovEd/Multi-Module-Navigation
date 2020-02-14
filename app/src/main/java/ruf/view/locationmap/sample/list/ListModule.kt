package ruf.view.locationmap.sample.list

import ruf.view.locationmap.navigator.FragmentModule
import toothpick.ktp.binding.bind

class ListModule : FragmentModule(ListFragment::class) {

    init {
        bind<ListRouter>().toClass<ListRouter>().singleton()
        bind<ListPresenter>().toClass<ListPresenter>().singleton()
    }
}