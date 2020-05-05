package ruf.view.mediator.list

import kotlinx.android.parcel.Parcelize
import ruf.view.core.IRouterClass

@Parcelize
object ListRouterClass : IRouterClass<ListRouter> {

    override val kClass get() = ListRouter::class
}