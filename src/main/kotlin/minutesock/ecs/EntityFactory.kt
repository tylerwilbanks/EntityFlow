package minutesock.ecs

class EntityFactory {
    private var currentId = -1

    fun createEntity(entityId: Int = -1, components: MutableList<Component<*>> = mutableListOf()): Entity {
        currentId += 1
        return Entity(id = currentId, entityId = entityId, components = components)
    }
}