package minutesock.ecs.engine

import minutesock.ecs.Component
import minutesock.ecs.Entity
import minutesock.ecs.EntityFactory
import minutesock.ecs.IterativeSystem

interface Engine {
    val entityFactory: EntityFactory
    val entities: MutableList<Entity>
    val iterativeSystems: MutableList<IterativeSystem>
    fun createEntity(components: MutableSet<Component<*>> = mutableSetOf(), entityId: Int = -1): Entity
    fun update(delta: Long)
    fun addSystems(vararg iterativeSystem: IterativeSystem)
}

