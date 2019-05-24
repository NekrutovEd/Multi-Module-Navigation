package ruf.view.locationmap

import android.content.Context
import android.location.Location
import com.tomtom.online.sdk.common.location.LatLng

interface MapsContract {
    interface View {
        fun pullListGadget(listGadget: List<LatLng>)
        fun pullListServer(listServer: List<LatLng>)
    }

    interface Presenter {
        fun attachView(view: View)
        fun stop()

        fun pushLocationGadget(currentLocation: Location)
        fun saveServerIp(context: Context, serverIp: String)
    }
}
