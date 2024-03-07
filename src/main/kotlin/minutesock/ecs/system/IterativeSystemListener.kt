package minutesock.ecs.system

import minutesock.ecs.Entity
import kotlin.reflect.KClass

interface IterativeSystemListener {
    fun isInterestedInThisSystem(fromSystem: KClass<out IterativeSystem>): Boolean

    fun onPreUpdate(event: SystemEvent.PreUpdate)
    fun onPostUpdate(event: SystemEvent.PostUpdate)
}