package minutesock.ecs

import kotlin.reflect.KClass

class Entity(
    val id: Int,
    val entityId: Int = -1,
    val components: MutableSet<Component<*>> = mutableSetOf()
) {

    internal val componentClasses: MutableSet<KClass<out Component<*>>> = components.map { it::class }.toMutableSet()

    fun hasComponents(vararg types: KClass<out Component<*>>): Boolean {
        return types.all { type -> components.any { it::class == type } }
    }

    inline fun <reified C : Component<C>> requireComponent(): C {
        return components.find { it::class == C::class } as C
    }

    inline fun <reified C : Component<C>> component(): C? {
        return components.find { it::class == C::class } as? C
    }

    fun <C> removeComponents(vararg components: Component<C>) {
        this.components.removeAll(components.toSet())
        componentClasses.removeAll(components.map { it::class })
    }

    fun removeComponents(vararg componentKClasses: KClass<out Component<*>>) {
        componentKClasses.forEach { componentClass: KClass<out Component<*>> ->
            components.removeAll { it::class == componentClass }
            componentClasses.removeAll {  it::class == componentClass::class }
        }
    }

    fun addComponents(vararg additionalComponents: Component<*>) {
        val add = additionalComponents.toSet()
        if (additionalComponents.size != add.size) {
            throw IllegalArgumentException("Entities cannot have duplicate component types.")
        }
        if (add.any { componentClasses.contains(it::class) }) {
            throw IllegalArgumentException("Entities cannot have duplicate component types.")
        }
        components.addAll(add)
        componentClasses.addAll(add.map { it::class })
    }
}

