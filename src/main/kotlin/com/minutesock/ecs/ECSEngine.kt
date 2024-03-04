package com.main.ecs


interface ECSEngine {
    val entityFactory: EntityFactory
    val entities: MutableList<Entity>
    val systems: MutableList<System<*>>
    val entityFamilyMap: EntityFamilyMap
    fun createEntity(components: MutableList<Component<*>> = mutableListOf(), entityId: Int = -1): Entity
    fun update(delta: Long)
    fun addSystems(vararg system: System<*>)
}

