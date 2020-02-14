package ruf.view.locationmap.sample.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list.view.*
import ruf.view.locationmap.R
import ruf.view.locationmap.navigator.FragmentModule.Companion.injectScope
import ruf.view.locationmap.sample.IView
import toothpick.ktp.delegate.inject


class ListFragment : Fragment(), IView {

    // Делаем зависимость от Presenter (Все зависимости стоит делать через интерфейсы, а не на прямую как тут)
    private val presenter: ListPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Данной строкой инжектим скоуп модуля к Fragment.
        // arguments уже содержит необходимые для этого данные.
        // Никаких newInstance у фрагмента не нужно. Работать с arguments можно, но не нужно.
        injectScope<ListModule>(arguments)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false).apply {
            open_detail.setOnClickListener { presenter.openDetail() }
            add_module.setOnClickListener { presenter.addModule(R.id.child_container) }
            remove_module.setOnClickListener { presenter.removeModule() }
            show_dialog.setOnClickListener { presenter.showDialog() }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Привязываемся к презентору для работы
        presenter.attachView(this)
    }

    override fun onDestroyView() {
        // Отвязываемся, чтобы презентор не общался с пустотой. Гиблое это дело, общаться с пустотой.
        presenter.detachView()
        super.onDestroyView()
    }

    // Двигаем в ListPresenter
}