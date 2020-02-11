package ruf.view.locationmap.navigator.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_detail.view.*
import ruf.view.locationmap.R
import ruf.view.locationmap.navigator.FragmentModule.Companion.injectScope
import toothpick.ktp.delegate.inject

class DetailFragment : Fragment() {

    private val presenter: DetailPresenter by inject()

    companion object {
        fun newInstance() = DetailFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectScope<DetailModule>(arguments)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        view.name.text = presenter.data.text
        view.button1.setOnClickListener { presenter.addDetail() }
        view.button2.setOnClickListener { presenter.closeDetail() }
        return view
    }
}