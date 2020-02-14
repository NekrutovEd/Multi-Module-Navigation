package ruf.view.locationmap.sample.detail

import ruf.view.locationmap.navigator.FragmentModule
import toothpick.ktp.binding.bind

// Передать данные в другой фрагмент можно через Bundle в newInstance,
//   а мы будем делать по уму и передавать данные в Scope, ибо фрагменту они не нужны, а через SharedModel.. зачем нам лишняя сущность?
// Хочешь вызвать наш модуль, будь добр предоставить нужные нам данные.
class DetailModule(data: DetailData) : FragmentModule(DetailFragment::class) {

    init {
        // А мы получим их там, где они нужны. (В презентере! Не стоит этим злоупотреблять и получать их где-то еще.)
        bind<DetailData>().toInstance(data)
        bind<DetailRouter>().toClass<DetailRouter>().singleton()
        bind<DetailPresenter>().toClass<DetailPresenter>().singleton()
    }

    // Го в DetailFragment
}