package ruf.view.feature_detail_presentation

interface IDetailRouter {

    fun addDetail(data: DetailData)

    fun replace(data: DetailData)

    fun back()

    fun closeDetail()
}