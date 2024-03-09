package minutesock.ecs

object EntityWorld {
    private val entityFactory: EntityFactory = EntityFactory()
    val entities: MutableList<Entity> = mutableListOf()

    fun createEntities(vararg entityCreationConfig: EntityCreationConfig): List<Entity> {
        val entities = mutableListOf<Entity>()
        entityCreationConfig.forEach { config: EntityCreationConfig ->
            entities.add(createEntity(config.components, config.entityId))
        }
        return entities
    }

    fun createEntity(components: MutableSet<Component<*>>, entityId: Int = -1): Entity {
        return entityFactory.createEntity(
            entityId = entityId,
            components = components,
        ).also { entity ->
            entities += entity
        }
    }
}