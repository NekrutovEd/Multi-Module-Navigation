package ruf.view.locationmap

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ruf.view.locationmap.navigator.Navigator
import ruf.view.locationmap.navigator.NavigatorModule
import ruf.view.locationmap.navigator.NavigatorModule.Companion.injectNavigator
import ruf.view.locationmap.navigator.list.ListModule
import toothpick.ktp.delegate.inject



class MainActivity : AppCompatActivity() {

    private val mainNavigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)

        NavigatorModule(R.id.container, supportFragmentManager).installModule()
        injectNavigator(R.id.container)
        mainNavigator.showFragmentScope(ListModule())
    }
}