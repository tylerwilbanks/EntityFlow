package minutesock.ecs.system

// todo-tyler have a think about this, not sure if I like it.
object SystemEventBus {
    private val iterativeSystemListeners = mutableListOf<IterativeSystemListener>()

    fun addListener(listener: IterativeSystemListener) {
        iterativeSystemListeners.add(listener)
    }

    fun removeListener(listener: IterativeSystemListener) {
        iterativeSystemListeners.remove(listener)
    }

    fun clearListeners() {
        iterativeSystemListeners.clear()
    }

    fun onEvent(event: SystemEvent) {
        when (event) {
            is SystemEvent.PreUpdate -> {
                iterativeSystemListeners.forEach {
                    it.onPreUpdate(event)
                }
            }

            is SystemEvent.PostUpdate -> {
                iterativeSystemListeners.forEach {
                    it.onPostUpdate(event)
                }
            }
        }
    }
}