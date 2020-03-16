package ruf.view.locationmap.sample.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list.view.*
import ruf.view.locationmap.R
import ruf.view.locationmap.navigator.FragmentModule.Companion.injectScope
import ruf.view.locationmap.navigator.INavigatorLifeCycle
import ruf.view.locationmap.navigator.NavigatorProvider
import ruf.view.locationmap.sample.IView
import ruf.view.locationmap.sample.ListNavigator
import toothpick.InjectConstructor
import toothpick.ktp.delegate.inject


class ListFragment : Fragment(), IView {

    // Делаем зависимость от Presenter (Все зависимости стоит делать через интерфейсы, а не на прямую как тут)
    private val presenter: ListPresenter by inject()

    private val navigator: INavigatorLifeCycle by inject(ListNavigator::class)

    override fun onCreate(savedInstanceState: Bundle?) {
//        NavigatorModule(R.id.child_container, arguments, savedInstanceState).installModule()


        // Данной строкой инжектим скоуп модуля к Fragment.
        // arguments уже содержит необходимые для этого данные.
        // Никаких newInstance у фрагмента не нужно. Работать с arguments можно, но не нужно.
        injectScope<ListModule>(arguments)

        super.onCreate(savedInstanceState)

        // Инжектим навигатор модуль(содержит только навигатор) на вызванный объект(this.injectNavigator(...)) с помощью его уникального идентификатора.
//        injectNavigator(arguments)

//        presenter.injectNavigator(arguments)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false).apply {
            open_detail.setOnClickListener { presenter.openDetail() }
            add_module.setOnClickListener { presenter.addModule() }
            remove_module.setOnClickListener { navigator.destroy() }
            show_dialog.setOnClickListener { presenter.showDialog() }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Привязываемся к презентору для работы
        presenter.attachView(this)
    }

    override fun onResume() {
        navigator.attachFragmentManager(childFragmentManager)
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        // Отвязываем FragmentManager, т.к. он протухает.
        // Навигатор остается жив и поддерживает работу с ним.
        navigator.detachFragmentManager()
    }

    override fun onDestroyView() {
        // Отвязываемся, чтобы презентор не общался с пустотой. Гиблое это дело, общаться с пустотой.
        presenter.detachView()
        super.onDestroyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        navigator.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        navigator.onViewStateRestored(savedInstanceState)
    }

    @InjectConstructor
    class ListNavigatorProvider : NavigatorProvider(R.id.child_container)

    // Двигаем в ListPresenter
}