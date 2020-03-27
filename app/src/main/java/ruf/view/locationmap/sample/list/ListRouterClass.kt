package ruf.view.locationmap.sample.list

import kotlinx.android.parcel.Parcelize
import ruf.view.core.RouterClass

@Parcelize
object ListRouterClass : RouterClass<ListRouter> {
    override val kClass get() = ListRouter::class
}