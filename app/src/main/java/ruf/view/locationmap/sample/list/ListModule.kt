package ruf.view.locationmap.sample.list

import kotlinx.android.parcel.Parcelize
import ruf.view.locationmap.navigator.FragmentModule
import ruf.view.locationmap.navigator.INavigatorCommand
import ruf.view.locationmap.navigator.INavigatorLifeCycle
import ruf.view.locationmap.sample.ListNavigator
import toothpick.Scope
import toothpick.ktp.binding.bind

// Наследуем FragmentModule и передаем ему в параметр класс стартового фрагмента.
@Parcelize
class ListModule : FragmentModule(ListFragment::class) {


    // Прописываем все необходимые зависимости(обычный модуль DI)
    init {
        val provider = ListFragment.ListNavigatorProvider()

        bind<INavigatorLifeCycle>().withName(ListNavigator::class).toProviderInstance(provider)
        bind<INavigatorCommand>().withName(ListNavigator::class).toProviderInstance(provider)
        bind<ListRouter>().toClass<ListRouter>().singleton()
        bind<ListPresenter>().toClass<ListPresenter>().singleton()
    }

    // Обычно мы еще от чего нибудь зависим, например RepositoryModule или ApiModule.
    // Не вопрос. Переопределяем openDependentScopes и добавляем в иерархию все необходимое.
    override fun Scope.openDependentScopes(): Scope = this
    /*.openSubScope(RepositoryModule::class)*/
    /*.openSubScope(ApiModule::class)*/

    // И идем в ListFragment
}
