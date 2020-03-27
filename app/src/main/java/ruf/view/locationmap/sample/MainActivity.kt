package ruf.view.locationmap.sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ruf.view.locationmap.R
import ruf.view.locationmap.library.IOnBackPressed

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)

        supportFragmentManager.findFragmentByTag(TAG_CONTAINER)
            ?: supportFragmentManager.beginTransaction()
                .replace(R.id.container, ContainerFragment(), TAG_CONTAINER)
                .addToBackStack(TAG_CONTAINER)
                .commit()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentByTag(TAG_CONTAINER) as? IOnBackPressed
        if (fragment?.onBackPressed() == false) finish()
    }
}

private const val TAG_CONTAINER = "ContainerFragment"

fun log(string: String) = Log.i("NAVIGATOR", string)