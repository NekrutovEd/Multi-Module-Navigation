package ruf.view.mediator.list

import kotlinx.android.parcel.Parcelize
import ruf.view.core.RouterClass
import ruf.view.mediator.list.ListRouter

@Parcelize
object ListRouterClass : RouterClass<ListRouter> {

    override val kClass get() = ListRouter::class
}