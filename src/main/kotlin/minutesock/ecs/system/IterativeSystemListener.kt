package minutesock.ecs.system

import minutesock.ecs.Entity
import kotlin.reflect.KClass

interface IterativeSystemListener {
    fun isInterestedInThisSystem(fromSystem: KClass<out IterativeSystem>): Boolean

    fun onPreUpdate(fromSystem: KClass<out IterativeSystem>, delta: Long, entities: List<Entity>)
    fun onPostUpdate(fromSystem: KClass<out IterativeSystem>, delta: Long, entities: List<Entity>)
}