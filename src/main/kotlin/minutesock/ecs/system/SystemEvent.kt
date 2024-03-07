package minutesock.ecs.system

import minutesock.ecs.Entity
import kotlin.reflect.KClass

sealed class SystemEvent {
    data class PreUpdate(val fromSystem: KClass<out IterativeSystem>, val delta: Long, val entities: List<Entity>) :
        SystemEvent()

    data class PostUpdate(val fromSystem: KClass<out IterativeSystem>, val delta: Long, val entities: List<Entity>) :
        SystemEvent()
}