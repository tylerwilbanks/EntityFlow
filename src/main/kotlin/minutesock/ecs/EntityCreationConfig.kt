package org.example.minutesock.ecs

import minutesock.ecs.Component

data class EntityCreationConfig(
    val components: MutableList<Component<*>>,
    val entityId: Int = -1
)
