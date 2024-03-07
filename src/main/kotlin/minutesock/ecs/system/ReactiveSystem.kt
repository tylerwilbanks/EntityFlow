package minutesock.ecs.system

import minutesock.ecs.Entity
import kotlin.reflect.KClass

abstract class ReactiveSystem(enabled: Boolean = true) : System(enabled), IterativeSystemListener {
    // todo-tyler consolidate filter criteria into one place and somehow make that visible wherever data is coming from
    // so less entities get copied over this way

    override fun isInterestedInThisSystem(fromSystem: KClass<out IterativeSystem>): Boolean {
        // todo-tyler check if any annotation classes match
        TODO("Not yet implemented")
    }

    abstract override fun onPreUpdate(fromSystem: IterativeSystem, delta: Long, entities: List<Entity>)
    abstract override fun onPostUpdate(fromSystem: IterativeSystem, delta: Long, entities: List<Entity>)
}