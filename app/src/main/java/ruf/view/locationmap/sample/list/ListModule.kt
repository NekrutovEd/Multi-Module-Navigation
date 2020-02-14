package ruf.view.locationmap.sample.list

import ruf.view.locationmap.navigator.FragmentModule
import toothpick.Scope
import toothpick.ktp.binding.bind

// Наследуем FragmentModule и передаем ему в параметр класс стартового фрагмента.
class ListModule : FragmentModule(ListFragment::class) {

    // Прописываем все необходимые зависимости(обычный модуль DI)
    init {
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