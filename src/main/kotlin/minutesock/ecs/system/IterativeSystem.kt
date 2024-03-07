package minutesock.ecs.system

import minutesock.ecs.Entity


abstract class IterativeSystem(enabled: Boolean = true) : System(enabled) {
    abstract fun update(delta: Long, entities: List<Entity>)
}
