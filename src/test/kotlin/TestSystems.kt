package minutesock

import minutesock.ecs.*


@AnyComponents
@NoneOfComponents([ObstacleComponent::class])
internal class IterativeSystemWithAnyAndOtherValidAnnotation : IterativeSystem() {
    override fun update(delta: Long, entities: List<Entity>) = Unit
}

internal annotation class InvalidAnnotation

@InvalidAnnotation
internal class IterativeSystemWithInvalidAnnotation : IterativeSystem() {
    override fun update(delta: Long, entities: List<Entity>) = Unit
}

internal class IterativeSystemWithNoAnnotations : IterativeSystem() {
    override fun update(delta: Long, entities: List<Entity>) = Unit
}

@AllOfComponents([MovementComponent::class])
internal class MovementIterativeSystem : IterativeSystem() {
    override fun update(delta: Long, entities: List<Entity>) = Unit
}

@AllOfComponents([TransformComponent::class])
@NoneOfComponents([MovementComponent::class, TeamComponent::class])
internal class TeleportIterativeSystem : IterativeSystem() {
    override fun update(delta: Long, entities: List<Entity>) = Unit
}

@AnyComponents
internal class WhateverIterativeSystem : IterativeSystem() {
    override fun update(delta: Long, entities: List<Entity>) = Unit
}