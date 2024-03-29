package minutesock.ecs

import kotlin.reflect.KClass

class Entity(
    val id: Int,
    val entityId: Int = -1,
    @PublishedApi
    internal val components: MutableMap<KClass<out Component<*>>, Component<*>> = mutableMapOf()
) {

    fun hasComponents(vararg types: KClass<out Component<*>>): Boolean {
        return types.all { type -> components.keys.any { it == type } }
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <C : Component<C>> get(type: KClass<out Component<C>>): C {
        return components[type] as C
    }

    inline fun <reified C : Component<C>> getComponent(): C? {
        return components[C::class] as? C
    }

    fun <C> removeComponents(vararg components: Component<C>) {
        this.components.keys.removeAll(components.map { it::class }.toSet())
    }

    fun removeComponents(vararg componentKClasses: KClass<out Component<*>>) {
        this.components.keys.removeAll(componentKClasses.toSet())
    }

    fun addComponents(vararg additionalComponents: Component<*>) {
        if (RunMode.safe && additionalComponents.toList().groupingBy { it::class }.eachCount().filter { it.value > 1 }
                .isNotEmpty()) {
            throw EntityDuplicateComponentException()
        }
        val componentMap = additionalComponents.associateBy { it::class }
        if (RunMode.safe && componentMap.keys.any { components.keys.contains(it) }) {
            throw EntityDuplicateComponentException()
        }
        components.putAll(componentMap)
    }
}

