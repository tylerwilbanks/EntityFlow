package minutesock.ecs.system

import minutesock.ecs.Entity

abstract class ReactiveSystem(enabled: Boolean = true) : System(enabled), IterativeSystemListener {
    // todo-tyler consolidate filter criteria into one place and somehow make that visible wherever data is coming from
    // so less entities get copied over this way
    abstract override fun onPreUpdate(fromSystem: IterativeSystem, delta: Long, entities: List<Entity>)
    abstract override fun onPostUpdate(fromSystem: IterativeSystem, delta: Long, entities: List<Entity>)
}