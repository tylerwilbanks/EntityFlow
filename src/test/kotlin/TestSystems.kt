package minutesock

import minutesock.ecs.*


@AnyComponents
@NoneOfComponents([ObstacleComponent::class])
internal class SystemWithAnyAndOtherValidAnnotation : System() {
    override fun update(delta: Long, entities: List<Entity>, allEntities: List<Entity>) = Unit
}

internal annotation class InvalidAnnotation

@InvalidAnnotation
internal class SystemWithInvalidAnnotation : System() {
    override fun update(delta: Long, entities: List<Entity>, allEntities: List<Entity>) = Unit
}

internal class SystemWithNoAnnotations : System() {
    override fun update(delta: Long, entities: List<Entity>, allEntities: List<Entity>) = Unit
}

@AllOfComponents([MovementComponent::class])
internal class MovementSystem : System() {
    override fun update(delta: Long, entities: List<Entity>, allEntities: List<Entity>) = Unit
}

@AllOfComponents([TransformComponent::class])
@NoneOfComponents([MovementComponent::class])
internal class TeleportSystem : System() {
    override fun update(delta: Long, entities: List<Entity>, allEntities: List<Entity>) = Unit
}