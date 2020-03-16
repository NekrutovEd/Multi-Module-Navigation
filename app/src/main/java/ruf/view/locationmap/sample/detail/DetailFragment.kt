package ruf.view.locationmap.sample.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_detail.view.*
import ruf.view.locationmap.R
import ruf.view.locationmap.navigator.FragmentModule.Companion.injectScope
import ruf.view.locationmap.sample.IView
import toothpick.ktp.delegate.inject

class DetailFragment : Fragment(), IView {

    // Нужен презентер или вдруг другие зависимости(не надо других зависимостей -_-)? Окай
    private val presenter: DetailPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Вызвали одну строку при старте и все получили.                                                                псс..      можно даже в onAttach, главное, чтобы после инициализации, когда arguments уже добавились.
        injectScope<DetailModule>(arguments)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false).apply {
            name.text = presenter.data.text
            add.setOnClickListener { presenter.addDetail() }
            replace.setOnClickListener { presenter.replaceDetail() }
            delete.setOnClickListener { presenter.removeDetail() }
            close.setOnClickListener { presenter.closeDetail() }
        }
    }

    //А че в DetailPresenter?
}