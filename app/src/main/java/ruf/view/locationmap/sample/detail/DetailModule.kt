package ruf.view.locationmap.sample.detail

import ruf.view.locationmap.navigator.FragmentModule
import toothpick.ktp.binding.bind

class DetailModule(data: DetailData) : FragmentModule(DetailFragment::class) {

    init {
        bind<DetailData>().toInstance(data)
        bind<DetailRouter>().toClass<DetailRouter>().singleton()
        bind<DetailPresenter>().toClass<DetailPresenter>().singleton()
    }
}