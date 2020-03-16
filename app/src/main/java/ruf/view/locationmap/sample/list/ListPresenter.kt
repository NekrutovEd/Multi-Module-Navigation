package ruf.view.locationmap.sample.list

import ruf.view.locationmap.sample.IPresenter
import ruf.view.locationmap.sample.IView
import toothpick.InjectConstructor

// Все зависимости презентера прописываем в конструкторе.
@InjectConstructor
class ListPresenter(private val router: ListRouter) : IPresenter {

    private var view: IView? = null

    // Инжектим внутренний навигатор, чтобы можно было управлять им. Мы в ответе за тех кого приручили.
    // Инекцию сделает фрамент, до атача, так что здесь нужно просто ждать.

    override fun attachView(view: IView) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    // Мы можем и в презентере получить старший навигатор, но лучше делегировать эту заботу роутеру.
    fun openDetail() = router.openDetail()

    fun addModule() {
        // Заменяем его текущий модуль на новый ListModule.
        // Если навигатор будет пустой, то новый модуль просто добавится первым в стеке.
        router.addListModule()
    }

    fun showDialog() = router.showDialog()

    // Погнали в ListRouter
}