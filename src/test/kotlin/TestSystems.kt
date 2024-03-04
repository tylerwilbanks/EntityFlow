package minutesock

import minutesock.ecs.AnyComponents
import minutesock.ecs.Entity
import minutesock.ecs.NoneOfComponents
import minutesock.ecs.System


@AnyComponents
@NoneOfComponents([ObstacleComponent::class])
internal class SystemWithAnyAndOtherValidAnnotation : System<SystemWithAnyAndOtherValidAnnotation>() {
    override fun update(delta: Long, entities: List<Entity>, allEntities: List<Entity>) = Unit
}

internal annotation class InvalidAnnotation

@InvalidAnnotation
internal class SystemWithInvalidAnnotation : System<SystemWithInvalidAnnotation>() {
    override fun update(delta: Long, entities: List<Entity>, allEntities: List<Entity>) = Unit
}

internal class SystemWithNoAnnotations : System<SystemWithNoAnnotations>() {
    override fun update(delta: Long, entities: List<Entity>, allEntities: List<Entity>) = Unit
}