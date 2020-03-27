package ruf.view.multi_module_navigation.navigator

import ruf.view.multi_module_navigation.command.ICommand
import java.util.*

class Buffer {

    private val queue = LinkedList<ICommand>()

    fun add(command: ICommand) {
        queue.add(command)
    }

    fun executeAll(nav: INavigatorManager) {
        while (queue.isNotEmpty()) {
            queue.poll().execute(nav)
        }
    }
}