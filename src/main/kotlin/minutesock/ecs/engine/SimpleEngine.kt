package minutesock.ecs.engine

import minutesock.ecs.*

class SimpleEngine(
    override val entityFactory: EntityFactory = EntityFactory(),
    override val iterativeSystems: MutableList<IterativeSystem> = mutableListOf(),
    override val entities: MutableList<Entity> = mutableListOf()
) : Engine {

    override fun createEntity(components: MutableSet<Component<*>>, entityId: Int): Entity {
        return entityFactory.createEntity(
            entityId = entityId,
            components = components,
        ).also { entity ->
            entities.add(entity)
        }
    }

    override fun update(delta: Long) {
        for (system in iterativeSystems) {
            if (!system.enabled) continue
            val filteredEntities = system.getFilteredEntities(entities)
            system.preUpdate(delta, filteredEntities)
            system.update(delta, filteredEntities)
            system.postUpdate(delta, filteredEntities)
        }
    }

    override fun addSystems(vararg iterativeSystem: IterativeSystem) {
        this.iterativeSystems.addAll(iterativeSystem)
    }
}
