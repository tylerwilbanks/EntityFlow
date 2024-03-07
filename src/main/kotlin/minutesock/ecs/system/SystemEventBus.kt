package minutesock.ecs.system

// todo-tyler have a think about this, not sure if I like it.
object SystemEventBus {
    private val iterativeSystemListeners = mutableListOf<IterativeSystemListener>()

    fun addListeners(vararg listener: IterativeSystemListener) {
        iterativeSystemListeners.addAll(listener.toSet())
    }

    fun removeListeners(vararg listener: IterativeSystemListener) {
        iterativeSystemListeners.removeAll(listener.toSet())
    }

    fun clearListeners() {
        iterativeSystemListeners.clear()
    }

    fun onEvent(event: SystemEvent) {
        when (event) {
            is SystemEvent.PreUpdate -> {
                iterativeSystemListeners.forEach {
                    if (it.isInterestedInThisSystem(event.fromSystem)) {
                        it.onPreUpdate(event.fromSystem, event.delta, event.entities)
                    }
                }
            }

            is SystemEvent.PostUpdate -> {
                iterativeSystemListeners.forEach {
                    if (it.isInterestedInThisSystem(event.fromSystem)) {
                        it.onPostUpdate(event.fromSystem, event.delta, event.entities)
                    }
                }
            }
        }
    }
}