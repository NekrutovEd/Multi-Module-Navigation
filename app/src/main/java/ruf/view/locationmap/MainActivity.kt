package ruf.view.locationmap

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


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