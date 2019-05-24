package ruf.view.locationmap

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.tomtom.online.sdk.common.location.LatLng
import com.tomtom.online.sdk.map.*
import com.tomtom.online.sdk.map.model.MapTilesType


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, MapsContract.View {

    private lateinit var mMap: TomtomMap
    private val presenter: MapsContract.Presenter = MapsPresenter

    private var routeGadget: RouteBuilder? = null
    private var routeServer: RouteBuilder? = null

    private var routeStyleGadget = RouteStyleBuilder.create()
        .withFillColor(Color.CYAN)
        .withOutlineColor(Color.DKGRAY)
        .build()

    private var routeStyleServer = RouteStyleBuilder.create()
        .withFillColor(Color.GREEN)
        .withOutlineColor(Color.DKGRAY)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as MapFragment
        mapFragment.getAsyncMap(this)

        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.stop()
    }

    override fun onMapReady(tomtomMap: TomtomMap) {
        mMap = tomtomMap
        mMap.uiSettings.mapTilesType = MapTilesType.VECTOR
        mMap.isMyLocationEnabled = true

        mMap.userLocation?.also { location ->
            mMap.centerOn(
                CameraPosition.builder(
                    LatLng(location.latitude, location.longitude)
                ).zoom(13.0).build()
            )
        }

        mMap.addLocationUpdateListener { currentLocation ->
            presenter.pushLocationGadget(currentLocation)
        }
    }

    override fun pullListGadget(listGadget: List<LatLng>) {
        routeGadget = RouteBuilder(listGadget)
            .style(routeStyleGadget)
        updateRoutes()
    }

    override fun pullListServer(listServer: List<LatLng>) {
        routeServer = RouteBuilder(listServer)
            .style(routeStyleServer)
        updateRoutes()
    }

    private fun updateRoutes() {
        if (this::mMap.isInitialized) {
            mMap.clearRoute()
            routeGadget?.also { mMap.addRoute(it) }
            routeServer?.also { mMap.addRoute(it) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.server_ip) {
            AlertDialog.Builder(this).apply {
                setTitle("SERVER IP")
                val input = EditText(this@MapsActivity)
                setView(input)
                setPositiveButton("Ok") { _, _ ->
                    presenter.saveServerIp(this@MapsActivity, input.text.toString())
                }
                setNegativeButton("Cancel") { _, _ -> }
                show()
            }
        }
        return true
    }
}