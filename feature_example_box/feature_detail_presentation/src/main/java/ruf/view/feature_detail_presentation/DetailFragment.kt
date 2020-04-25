package ruf.view.feature_detail_presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_detail.view.*
import ruf.view.core.BaseFragment
import ruf.view.core.IView
import ruf.view.multi_module_navigation.module.FragmentModule.Companion.injectScope
import toothpick.ktp.delegate.inject

internal class DetailFragment : BaseFragment(), IView {

    override val presenter by inject<DetailPresenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        injectScope(arguments)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false).apply {
            val text = "${presenter.listData.textData} ${presenter.data.text}"
            name.text = text
            add.setOnClickListener { presenter.addDetail() }
            replace.setOnClickListener { presenter.replaceDetail() }
            delete.setOnClickListener { presenter.removeDetail() }
            close.setOnClickListener { presenter.closeDetail() }
        }
    }
}