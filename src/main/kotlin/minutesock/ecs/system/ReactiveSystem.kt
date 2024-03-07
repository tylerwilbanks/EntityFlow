package minutesock.ecs.system

import minutesock.ecs.Entity
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

    abstract override fun onPreUpdate(fromSystem: KClass<out IterativeSystem>, delta: Long, entities: List<Entity>)
    abstract override fun onPostUpdate(fromSystem: KClass<out IterativeSystem>, delta: Long, entities: List<Entity>)
}