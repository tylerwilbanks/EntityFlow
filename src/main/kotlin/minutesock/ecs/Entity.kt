package minutesock.ecs

import kotlin.reflect.KClass

class Entity(
    val id: Int,
    val entityId: Int = -1,
    val components: MutableList<Component<*>> = mutableListOf()
) {

    val componentClasses: List<KClass<out Component<*>>> = components.map { it::class }

    fun hasComponents(vararg types: KClass<out Component<*>>): Boolean {
        return types.all { type -> components.any { it::class == type } }
    }

    inline fun <reified C : Component<C>> requireComponent(): C {
        return components.find { it::class == C::class } as C
    }

    inline fun <reified C : Component<C>> component(): C? {
        return components.find { it::class == C::class } as? C
    }
}

