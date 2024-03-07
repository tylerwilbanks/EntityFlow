package minutesock

import minutesock.ecs.system.AllOfComponents
import minutesock.ecs.system.AnyComponents
import minutesock.ecs.system.NoneOfComponents
import minutesock.ecs.system.System

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
