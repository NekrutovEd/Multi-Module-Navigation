package ruf.view.locationmap.data

import com.tomtom.online.sdk.common.location.LatLng
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST

interface MapsServerApi {
    @GET("s")
    fun getServerCoordinate(): Single<LatLng>

    @POST("g")
    fun postGadgetCoordinate(coordinate: LatLng)
}