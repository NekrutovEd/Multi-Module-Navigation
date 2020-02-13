package ruf.view.locationmap.navigator.detail

import ruf.view.locationmap.navigator.FragmentModule
import toothpick.ktp.binding.bind

class DetailModule(data: DetailData) : FragmentModule() {

    override fun createFragment() = DetailFragment()

    init {
        bind<DetailData>().toInstance(data)
        bind<DetailRouter>().toClass<DetailRouter>().singleton()
        bind<DetailPresenter>().toClass<DetailPresenter>().singleton()
    }
}