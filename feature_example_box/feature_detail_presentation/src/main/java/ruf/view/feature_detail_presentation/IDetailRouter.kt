package ruf.view.feature_detail_presentation

import ruf.view.core.IRouter

interface IDetailRouter : IRouter {

    fun addDetail(data: DetailData)

    fun replace(data: DetailData)

    fun back()

    fun closeDetail()
}