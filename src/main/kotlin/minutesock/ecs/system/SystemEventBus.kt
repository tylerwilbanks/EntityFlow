package minutesock.ecs.system

// todo-tyler have a think about this, not sure if I like it.
object SystemEventBus {
    private val iterativeSystemListeners = mutableListOf<IterativeSystemListener>()

    fun onPreUpdate() {

    }

    fun onPostUpdate() {

    }
}