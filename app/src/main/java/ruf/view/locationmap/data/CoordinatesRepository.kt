package ruf.view.locationmap.data

import com.tomtom.online.sdk.common.location.LatLng
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

object CoordinatesRepository {

//    fun observeCoordinatesServer(): Observable<LatLng> {
//        return NetworkService.mapsServerApi.getServerCoordinate()
//            .repeatWhen { it.delay(1, TimeUnit.SECONDS) }
//            .subscribeOn(Schedulers.io())
//            .toObservable()
//    }
//
//    fun pushCoordinatesGadget(coordinates: LatLng) {
//        NetworkService.mapsServerApi.postGadgetCoordinate(coordinates)
//    }


    private var latitude: Double? = null
    private var longitude: Double? = null

    fun observeCoordinatesServer(): Observable<LatLng> {
        return Observable.create<LatLng> { emmiter ->
            while (true) {
                latitude?.also { lat ->
                    longitude?.also { lon ->
                        emmiter.onNext(LatLng(lat + 1, lon + 1))
                    }
                }
                Thread.sleep(1000)
            }
        }.subscribeOn(Schedulers.io())
    }

    fun pushCoordinatesGadget(coordinates: LatLng) {
        latitude = coordinates.latitude
        longitude = coordinates.longitude
    }
}