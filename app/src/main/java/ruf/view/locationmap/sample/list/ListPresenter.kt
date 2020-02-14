package ruf.view.locationmap.sample.list

import android.support.annotation.IdRes
import ruf.view.locationmap.navigator.INavigator
import ruf.view.locationmap.sample.IPresenter
import ruf.view.locationmap.sample.IView
import toothpick.InjectConstructor

// Все зависимости презентера прописываем в конструкторе.
@InjectConstructor
class ListPresenter(private val router: ListRouter) : IPresenter {

    private var view: IView? = null

    // Если хотим создать вложенный навигатор, то стоит его сохранить и управлять им, чтобы он не остался в пямяти.
    // Он конечно сам уничтожится вместе с родительским навигатором, но когда это произойдет не известно.
    private var newNavigator: INavigator? = null

    override fun attachView(view: IView) {
        this.view = view
        // Если у нас есть вложенные навигаторы, то при появлении view, запрашиваем у нее FragmentManager и привязываем его к нашему навигатору.
        view.getChildFragmentManager().also {
            newNavigator?.attachFragmentManager(it)
        }
    }

    override fun detachView() {
        view = null
        // Отвязывать тоже стоит не забывать. Криминального ничего не будет, старшие навигаторы за ним проследят, но лучше и нам приглядеть.
        newNavigator?.detachFragmentManager()
    }

    // Мы можем и в презентере получить старший навигатор, но лучше делегировать эту заботу роутеру.
    fun openDetail() = router.openDetail()

    fun addModule(@IdRes containerId: Int) {

        // Создаем свой внутренний навигатор, который будет работать в containerId.
        newNavigator = router.createNavigator(containerId)

        // Заменяем его текущий модуль на новый ListModule.
        // Если навигатор будет пустой, то новый модуль просто добавится первым в стеке.
        newNavigator?.replace(ListModule())

        // Если хотим, чтобы наш навигатор отразил стое состояние на containerId, то стоит передать ему FragmentManager
        view?.getChildFragmentManager()?.also {
            newNavigator?.attachFragmentManager(it)
        }
    }

    fun showDialog() = router.showDialog()

    // Не забудь уничтожить навигатор, когда соберешься уходить ! ! !                                                      При нажатии кнопки назад
    // Страшного ничего не будет, но лучше не забывать и напоминать другим.
    fun removeModule() = newNavigator?.destroy()

    // Погнали в ListRouter
}