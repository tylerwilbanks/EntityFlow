package minutesock.ecs

data class EntityCreationConfig(
    val components: MutableList<Component<*>>,
    val entityId: Int = -1
)
