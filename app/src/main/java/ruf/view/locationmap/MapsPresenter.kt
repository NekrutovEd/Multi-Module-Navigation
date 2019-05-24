package ruf.view.locationmap

import android.content.Context
import android.location.Location
import android.preference.Preference
import android.preference.PreferenceManager
import com.tomtom.online.sdk.common.location.LatLng
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ruf.view.locationmap.data.CoordinatesRepository
import ruf.view.locationmap.data.PREF_KEY_SERVER_IP
import java.security.AccessControlContext

object MapsPresenter : MapsContract.Presenter {

    private var view: MapsContract.View? = null

    private val listGadget = mutableListOf<LatLng>()
    private val listServer = mutableListOf<LatLng>()

    private val compositeDisposable = CompositeDisposable()

    override fun attachView(view: MapsContract.View) {
        this.view = view
        view.pullListServer(listServer)
        compositeDisposable.add(
            CoordinatesRepository.observeCoordinatesServer()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { latLngServer ->
                    if (listServer.isEmpty() || listServer.last() != latLngServer) {
                        listServer.add(latLngServer)
                        this.view?.pullListServer(listServer)
                    }
                }
        )
    }

    override fun stop() {
        view = null
        compositeDisposable.clear()
    }

    override fun pushLocationGadget(currentLocation: Location) {
        val latLngGadget = LatLng(currentLocation.latitude, currentLocation.longitude)
        CoordinatesRepository.pushCoordinatesGadget(latLngGadget)
        if (listGadget.isEmpty() || listGadget.last() != latLngGadget) {
            listGadget.add(latLngGadget)
            view?.pullListGadget(listGadget)
        }
    }

    override fun saveServerIp(context: Context, serverIp: String) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(PREF_KEY_SERVER_IP, serverIp)
            .apply()
    }
}