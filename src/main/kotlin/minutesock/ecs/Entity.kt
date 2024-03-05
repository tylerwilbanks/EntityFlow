package minutesock.ecs

import kotlin.reflect.KClass

class Entity(
    val id: Int,
    val entityId: Int = -1,
    val components: MutableList<Component<*>> = mutableListOf()
) {

    val componentClasses: List<KClass<out Component<*>>> = components.map { it::class }

    fun hasComponents(vararg types: KClass<out Component<*>>): Boolean =
        types.all { type -> components.any { it::class == type } }


    inline fun <reified C : Component<C>> getComponent(): C =
        components.find { it::class == C::class } as C
}

class EntityFactory {
    private var currentId = -1

    fun createEntity(entityId: Int = -1, components: MutableList<Component<*>> = mutableListOf()): Entity {
        currentId += 1
        return Entity(id = currentId, entityId = entityId, components = components)
    }
}
