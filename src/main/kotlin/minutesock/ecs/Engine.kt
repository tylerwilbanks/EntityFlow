package minutesock.ecs

interface Engine {
    val entityFactory: EntityFactory
    val entities: MutableList<Entity>
    val systems: MutableList<System>
    fun createEntity(components: MutableSet<Component<*>> = mutableSetOf(), entityId: Int = -1): Entity
    fun update(delta: Long)
    fun addSystems(vararg system: System)
}

