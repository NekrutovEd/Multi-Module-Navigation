package ruf.view.locationmap.sample.detail

import kotlinx.android.parcel.Parcelize
import ruf.view.locationmap.library.module.FragmentModule
import toothpick.ktp.binding.bind
import java.util.*

@Parcelize
data class DetailModule(private val data: DetailData, override val scopeName: String = UUID.randomUUID().toString()) :
    FragmentModule(DetailFragment::class) {

    init {
        bind<DetailData>().toInstance(data)
        bind<DetailRouter>().toClass<DetailRouter>().singleton()
        bind<DetailPresenter>().toClass<DetailPresenter>().singleton()
    }
}