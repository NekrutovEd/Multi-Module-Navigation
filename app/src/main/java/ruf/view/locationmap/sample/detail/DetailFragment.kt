package ruf.view.locationmap.sample.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_detail.view.*
import ruf.view.locationmap.R
import ruf.view.locationmap.library.module.FragmentModule.Companion.injectScope
import ruf.view.locationmap.sample.IView
import ruf.view.locationmap.sample.LogFragment
import toothpick.ktp.delegate.inject

class DetailFragment : LogFragment(), IView {

    private val presenter: DetailPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
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
}