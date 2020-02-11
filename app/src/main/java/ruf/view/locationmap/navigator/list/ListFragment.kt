package ruf.view.locationmap.navigator.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list.view.*
import ruf.view.locationmap.R
import ruf.view.locationmap.navigator.FragmentModule.Companion.injectScope
import toothpick.ktp.delegate.inject


class ListFragment : Fragment() {

    private val presenter: ListPresenter by inject()

    companion object {
        fun newInstance() = ListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectScope<ListModule>(arguments)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        view.open_detail.setOnClickListener { presenter.openDetail() }
        view.add_detail.setOnClickListener { presenter.addDetail() }
        return view
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }
}