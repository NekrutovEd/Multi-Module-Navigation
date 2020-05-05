package ruf.view.mediator.pager

import kotlinx.android.parcel.Parcelize
import ruf.view.core.IRouterClass

@Parcelize
object PagerRouterClass : IRouterClass<PagerRouter> {

    override val kClass get() = PagerRouter::class
}
