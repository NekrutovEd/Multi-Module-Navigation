package ruf.view.mediator.detail

import kotlinx.android.parcel.Parcelize
import ruf.view.core.IRouterClass

@Parcelize
object DetailRouterClass : IRouterClass<DetailRouter> {

    override val kClass get() = DetailRouter::class
}