package ruf.view.locationmap.sample.detail

import toothpick.InjectConstructor

// Хотел данные? Получи и распишись.
@InjectConstructor
class DetailPresenter(val data: DetailData, private val router: DetailRouter) {

    fun addDetail() = router.addDetail(data) // (forward) пошли вперед

    fun replaceDetail() = router.replace(data) // заменили шило на мыло

    fun removeDetail() = router.back() // пошли назад

    fun closeDetail() = router.closeDetail() // (backTo) телепортнулись назад

    // Вроде все. А в папку navigator не лезь, там не интересно -_о
}
