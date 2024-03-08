package minutesock

import minutesock.ecs.Entity
import minutesock.ecs.system.*

@AnyComponents
@NoneOfComponents([ObstacleComponent::class])
internal class SystemWithAnyAndOtherValidAnnotation : System()
internal annotation class InvalidAnnotation

@InvalidAnnotation
internal class SystemWithInvalidAnnotation : System()
internal class SystemWithNoAnnotations : System()

@AllOfComponents([MovementComponent::class])
internal class MovementSystem : System()

@AllOfComponents([TransformComponent::class])
@NoneOfComponents([MovementComponent::class, TeamComponent::class])
internal class TeleportSystem : System()

@AnyComponents
internal class WhateverSystem : System()


@AnyComponents
internal class IterativeSystem1(sortOrder: Int?) : IterativeSystem(sortOrder = sortOrder) {
    override fun update(delta: Long, entities: List<Entity>) = Unit
}

@AnyComponents
internal class IterativeSystem2(sortOrder: Int?) : IterativeSystem(sortOrder = sortOrder) {
    override fun update(delta: Long, entities: List<Entity>) = Unit
}

@AnyComponents
internal class IterativeSystem3(sortOrder: Int?) : IterativeSystem(sortOrder = sortOrder) {
    override fun update(delta: Long, entities: List<Entity>) = Unit
}