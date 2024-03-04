package minutesock.ecs.engine

import minutesock.ecs.*

class SimpleEngine(
    override val entityFamilyMap: EntityFamilyMap,
    override val entityFactory: EntityFactory = EntityFactory(),
    override val systems: MutableList<System<*>> = mutableListOf(),
    override val entities: MutableList<Entity> = mutableListOf()
) : Engine {

    override fun createEntity(components: MutableList<Component<*>>, entityId: Int): Entity {
        return entityFactory.createEntity(
            entityId = entityId,
            components = components,
            familyId = entityFamilyMap.getFamilyId(components)
        ).also { entity ->
            entities.add(entity)
        }
    }

    override fun update(delta: Long) {
        systems.forEach { system ->
            val filteredEntities = if (system.familyId != -1) system.filterByFamily(entities) else entities
            system.preUpdate(delta, filteredEntities, entities)
            system.update(delta, filteredEntities, entities)
            system.postUpdate(delta, filteredEntities, entities)
        }
    }

    override fun addSystems(vararg system: System<*>) {
        this.systems.addAll(system)
    }
}
