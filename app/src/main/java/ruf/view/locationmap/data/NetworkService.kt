package ruf.view.locationmap.data

import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val PREF_KEY_SERVER_IP = "SERVER_IP"
private const val URL = "https://тут_может_быть_ваша_реклама"

class NetworkService {
    companion object {
        var mapsServerApi: MapsServerApi = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build().create(MapsServerApi::class.java)
            private set
    }
}