package ruf.view.locationmap.sample.list

import android.support.annotation.IdRes
import ruf.view.locationmap.navigator.INavigator
import ruf.view.locationmap.navigator.INavigatorCommand
import ruf.view.locationmap.sample.detail.DetailData
import ruf.view.locationmap.sample.detail.DetailModule
import ruf.view.locationmap.sample.dialog.ExampleDialogModule
import toothpick.InjectConstructor

// Проставляем в зависимости INavigatorCommand, чтобы не быть бесполезными.
// INavigatorCommand всегда есть в scope, в виде навигатора в котором мы хранимся.
@InjectConstructor
class ListRouter(private val navigator: INavigatorCommand) {

    // Двинули вперед по стеку на DetailModule.
    fun openDetail() = navigator.forward(DetailModule(DetailData("First detail")))

    // Показ диалога не добавляет его в стек.
    // Если DialogFragmentModule передать в forward или другие команды, то он поведет себя как обычный FragmentModule(добавится в стек или др.), но отобразится все равно в виде диалога.
    // Если хочешь DialogFragmentModule использовать как FragmentModule, то не забудь оповестить навигатор при закрытии диалога, а то он так и останется в стеке и снова откроется при первой возможности.
    // А еще лучше использовать его по назначению, а не вот это вот все.
    fun showDialog() = navigator.showDialog(ExampleDialogModule())

    // Стартует новый навигатор для containerId. Если вызвать несколько стартов на одном контейнере, то они будут друг другу мешать. Не стоит так делать. Не зря называется START, а не RESTART
    fun createNavigator(@IdRes containerId: Int): INavigator = navigator.startNewNavigatorOn(containerId)

    // Можешь заглянуть в ExampleDialogModule и ExampleDialogFragment, это самый простой диалог, отображающий заранее заданный текст. Усложнять можно до бесконечности.
    // А я жду тебя в DetailModule
}