package ruf.view.locationmap.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import ruf.view.locationmap.R

// Навигация создана для Fragment и подходит когда делают SingleActivity или несколько базовых Activity.
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)

        // Activity является контейнером.
        // Стартовать навигацию можно где угодно.
        // Добавляем на нее фрагмент в виде контейнера, чтобы на нем стартануть навигацию.
        // ContainerFragment сделан для примера, что можно сделать старт навигации локальной фичи не затрагивая всего остального приложения.
        supportFragmentManager.findFragmentByTag("ContainerFragment")
            ?: supportFragmentManager.beginTransaction()
                .replace(R.id.container, ContainerFragment.newInstance(), "ContainerFragment")
                .addToBackStack("ContainerFragment")
                .commit()

        // Далее идем в ContainerFragment
    }

    override fun onBackPressed() {
        supportFragmentManager.findFragmentByTag("ContainerFragment")?.childFragmentManager?.popBackStack()
    }
}


fun log(string: String) = Log.i("NAVIGATOR", string)