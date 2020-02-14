package ruf.view.locationmap.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ruf.view.locationmap.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)

        supportFragmentManager.findFragmentById(R.id.container)
            ?: supportFragmentManager.beginTransaction()
                .add(R.id.container, ContainerFragment())
                .commit()
    }
}