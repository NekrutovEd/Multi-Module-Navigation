package ruf.view.locationmap.library.navigator

import ruf.view.locationmap.library.command.ICommand
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