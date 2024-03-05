package minutesock.ecs

data class EntityCreationConfig(
    val components: MutableSet<Component<*>>,
    val entityId: Int = -1
)
