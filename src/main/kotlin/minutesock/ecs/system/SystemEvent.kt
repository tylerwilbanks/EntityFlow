package minutesock.ecs.system

import minutesock.ecs.Entity

sealed class SystemEvent {
    data class PreUpdate(val fromSystem: IterativeSystem, val delta: Long, val entities: List<Entity>)
    data class PostUpdate(val fromSystem: IterativeSystem, val delta: Long, val entities: List<Entity>)
}