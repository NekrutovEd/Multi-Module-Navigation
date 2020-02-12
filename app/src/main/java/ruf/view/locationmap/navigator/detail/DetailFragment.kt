package ruf.view.locationmap.navigator.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_detail.view.*
import ruf.view.locationmap.R
import ruf.view.locationmap.navigator.FragmentModule.Companion.injectScope
import ruf.view.locationmap.navigator.IView
import toothpick.ktp.delegate.inject

class DetailFragment : Fragment(), IView {

    override val manager: FragmentManager?
        get() = childFragmentManager

    private val presenter: DetailPresenter by inject()

    companion object {
        fun newInstance() = DetailFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectScope<DetailModule>(arguments)
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