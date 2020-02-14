package ruf.view.locationmap.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ruf.view.locationmap.R
import ruf.view.locationmap.navigator.INavigator
import ruf.view.locationmap.navigator.INavigatorCommand
import ruf.view.locationmap.navigator.NavigatorModule
import ruf.view.locationmap.navigator.NavigatorModule.Companion.injectNavigator
import ruf.view.locationmap.sample.list.ListModule
import toothpick.ktp.delegate.inject

class ContainerFragment : Fragment() {

    // Инжектим стратовый навигатор, чтобы можно было управлять им. Мы в ответе за тех кого приручили.
    private val mainNavigator: INavigatorCommand by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Создаем и стартуем базовый навигационный модуль.                                                               Это можно сделать из любого места. Никаких контехтов не надо.
        // Первый параметр это контейнер к которому привяжется навигатор.
        // Второй параметр является уникальным идентификатором этого модуля в виде любого объекта. (Не обязательный)      Имей к нему доступ, если хочешь когда-нибудь получить навигатор.
        // По нему можно будет получить его с помощью injectNavigator(...)
        // Если создать второй модуль с тем же идентификатором до закрытия первого, то ничего не произойдет.
        // Работающим останется только первый.
        // Если первый был закрыт до старта второго, то второй стартанет, как будто первого и не было никогда.
        NavigatorModule(R.id.container, ContainerFragment::class).installModule()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.container_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инжектим навигатор модуль(содержит только навигатор) на вызванный объект(this.injectNavigator(...)) с помощью его уникального идентификатора.
        injectNavigator(ContainerFragment::class)

        // Если в навигаторе нету других модулей, тогда кладем в стек ListModule
        mainNavigator.forwardIfEmpty(ListModule())

        // Привязываем к навигатору FragmentManager, чтобы он мог отображать модули.
        // При привязке, он отобразит свое текущее состояние.
        (mainNavigator as? INavigator)?.attachFragmentManager(childFragmentManager)
        // Привязка доступна через интерфейс INavigator.
        // Чтобы убрать возможность получения его через DI - приходится кастить.
        // Получение должно происходить только в стартовом навигаторе так что такого каста больше нигде не должно быть.
        // Все внутренние навигаторы получаются через метод startNewNavigatorOn и хранятся локально.
    }

    override fun onDestroyView() {
        // Отвязываем FragmentManager, т.к. он протухает.
        // Навигатор остается жив и поддерживает работу с ним.
        (mainNavigator as? INavigator)?.detachFragmentManager()
        super.onDestroyView()
    }

    // Идем в ListModule
}