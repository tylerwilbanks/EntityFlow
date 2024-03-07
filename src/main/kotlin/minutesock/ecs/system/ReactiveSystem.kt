package minutesock.ecs.system

import kotlin.reflect.KClass

abstract class ReactiveSystem(enabled: Boolean = true) : System(enabled), IterativeSystemListener {

    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        SystemEventBus.addListeners(this)
    }

    override fun isInterestedInThisSystem(fromSystem: KClass<out IterativeSystem>): Boolean {
        // todo-tyler check if any annotation classes match
        TODO("Not yet implemented")
    }

    abstract override fun onPreUpdate(event: SystemEvent.PreUpdate)
    abstract override fun onPostUpdate(event: SystemEvent.PostUpdate)
}