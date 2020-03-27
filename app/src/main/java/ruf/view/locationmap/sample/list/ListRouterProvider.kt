package ruf.view.locationmap.sample.list

import kotlinx.android.parcel.Parcelize
import ruf.view.core.RouterClassProvider
import ruf.view.feature_list_presentation.IListRouter

@Parcelize
object ListRouterProvider : RouterClassProvider<IListRouter> {
    override fun get() = ListRouter::class
}