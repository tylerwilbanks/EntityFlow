package com.main.ecs

import kotlin.reflect.KClass

data class EntityFamilyMap(private val map: MutableMap<Int, List<KClass<out Component<*>>>>) {
    fun getFamilyId(components: List<Component<*>>): Int {
        for ((key, valueComponents) in map) {
            // Check if all types in 'valueComponents' are present in 'components'
            val allTypesInFamily = valueComponents.all { valueComponent ->
                components.any { it::class == valueComponent::class }
            }
            if (allTypesInFamily) {
                return key
            }
        }
        return -1
    }
}
