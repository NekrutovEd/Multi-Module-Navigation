package ruf.view.mediator.pager

import kotlinx.android.parcel.Parcelize
import ruf.view.core.RouterClass

@Parcelize
object PagerRouterClass : RouterClass<PagerRouter> {

    override val kClass get() = PagerRouter::class
}
