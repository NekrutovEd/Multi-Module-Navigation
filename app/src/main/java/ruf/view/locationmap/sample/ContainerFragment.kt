package ruf.view.locationmap.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ruf.view.locationmap.R
import ruf.view.locationmap.navigator.INavigator
import ruf.view.locationmap.navigator.NNN
import ruf.view.locationmap.navigator.NavigatorProvider
import ruf.view.locationmap.sample.list.ListModule

class ContainerFragment : Fragment() {

    // Инжектим стратовый навигатор, чтобы можно было управлять им. Мы в ответе за тех кого приручили.
    private val mainNavigator: INavigator by lazy { RootNavigatorProvider().get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Создаем и стартуем базовый навигационный модуль.                                                               Это можно сделать из любого места. Никаких контехтов не надо.
        // Первый параметр это контейнер к которому привяжется навигатор.
        // Второй параметр является уникальным идентификатором этого модуля в виде любого объекта. (Не обязательный)      Имей к нему доступ, если хочешь когда-нибудь получить навигатор.
        // По нему можно будет получить его с помощью injectNavigator(...)
        // Если создать второй модуль с тем же идентификатором до закрытия первого, то ничего не произойдет.
        // Работающим останется только первый.
        // Если первый был закрыт до старта второго, то второй стартанет, как будто первого и не было никогда.
        mainNavigator.onViewStateRestored(savedInstanceState)

        super.onCreate(savedInstanceState)


        // Если в навигаторе нету других модулей, тогда кладем в стек ListModule
        // В передаваемой лямбде, контекстом(this) является реализация интерфейса ICustomizationCommand.
        // Этот инерфейс позволяется задать необхотимые параметры для данной команды. Например собственную анимацию.
        mainNavigator.forwardIfEmpty {
            setCustomAnimations(R.anim.first_list_enter, R.anim.first_list_exit)
            ListModule()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.container_fragment, container, false)

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//    }

    override fun onResume() {
        super.onResume()
        // Привязываем к навигатору FragmentManager, чтобы он мог отображать модули.
        // При привязке, он отобразит свое текущее состояние.
        mainNavigator.attachFragmentManager(childFragmentManager)
        // Привязка доступна через интерфейс INavigator.
        // Чтобы убрать возможность получения его через DI - приходится кастить.
        // Получение должно происходить только в стартовом навигаторе так что такого каста больше нигде не должно быть.
        // Все внутренние навигаторы получаются через метод startNewNavigatorOn и хранятся локально.


//        mainNavigator.forwardIfEmpty {
//            setCustomAnimations(R.anim.first_list_enter, R.anim.first_list_exit)
//            ListModule()
//        }
//        childFragmentManager.findFragmentByTag("ContainerFragment")
//            ?: run {
//                childFragmentManager.beginTransaction()
//                    .setCustomAnimations(R.anim.first_list_enter, R.anim.first_list_exit)
//                    .add(R.id.container, listModule.createFragment(), "ContainerFragment")
//                    .addToBackStack("dsa")
//                    .commit()
//            }
    }

    override fun onPause() {
        // Отвязываем FragmentManager, т.к. он протухает.
        // Навигатор остается жив и поддерживает работу с ним.
        mainNavigator.detachFragmentManager()
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
//        log("onSaveInstanceState $outState")
        mainNavigator.onSaveInstanceState(outState)
//        log("onSaveInstanceState $outState")
//        outState.putSerializable(INDIVIDUALITY + "NavigatorModule", navigatorModuleScopeName)
//        outState.putString(INDIVIDUALITY, listModule.scopeName)
//        outState.putParcelableArrayList(INDIVIDUALITY + "LIST", ArrayList(Stack<DetailData>().apply {
//            add(DetailData2(1))
//            add(DetailData(1))
//            add(DetailData2(2))
//            add(DetailData(2))
//        }))
        super.onSaveInstanceState(outState)
//        log("onSaveInstanceState $outState")
    }

//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        log("onViewStateRestored $savedInstanceState")
//        super.onViewStateRestored(savedInstanceState)
//        log("onViewStateRestored $savedInstanceState")
////        (mainNavigator as? INavigator)?.onViewStateRestored(savedInstanceState)
//    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//    }

//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//        mainNavigator.onViewStateRestored(savedInstanceState)
//    }

    companion object {
        fun newInstance() = ContainerFragment().apply { arguments = Bundle() }

        private class RootNavigatorProvider : NavigatorProvider(R.id.container, NNN.Root.scopeName)
    }

    // Идем в ListModule
}