package minutesock.ecs.system

import minutesock.ecs.Entity
import kotlin.reflect.KClass

interface IterativeSystemListener {
    fun isInterestedInThisSystem(fromSystem: KClass<out IterativeSystem>): Boolean

    fun onPreUpdate(fromSystem: IterativeSystem, delta: Long, entities: List<Entity>)
    fun onPostUpdate(fromSystem: IterativeSystem, delta: Long, entities: List<Entity>)
}