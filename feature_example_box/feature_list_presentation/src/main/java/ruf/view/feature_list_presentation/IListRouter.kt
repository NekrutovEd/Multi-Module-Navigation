package ruf.view.feature_list_presentation

import ruf.view.core.IRouter

interface IListRouter : IRouter {

    fun openDetail()

    fun showDialog()

    fun addListModule(tag: String)

    fun removeListModule()
}
