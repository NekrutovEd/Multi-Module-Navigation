package ruf.view.locationmap.sample.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ruf.view.locationmap.sample.list.ListData
import ruf.view.multi_module_navigation.module.SharedModule
import toothpick.ktp.binding.bind

class ExampleSharedModule(tag: String, val name: String) : SharedModule() {

    override val scopeName = ScopeNameModel(name)

    init {
        bind<ListData>().toInstance(ListData(tag))
        bind<ScopeNameModel>().toInstance(scopeName)
    }

    @Parcelize
    data class ScopeNameModel(val scopeName: String) : Parcelable
}