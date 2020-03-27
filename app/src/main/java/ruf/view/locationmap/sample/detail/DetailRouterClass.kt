package ruf.view.locationmap.sample.detail

import kotlinx.android.parcel.Parcelize
import ruf.view.core.RouterClass

@Parcelize
object DetailRouterClass : RouterClass<DetailRouter> {
    override val kClass get() = DetailRouter::class
}