package minutesock.ecs.system

import minutesock.ecs.Entity

interface IterativeSystemListener {
    fun onPreUpdate(fromSystem: IterativeSystem, delta: Long, entities: List<Entity>)
    fun onPostUpdate(fromSystem: IterativeSystem, delta: Long, entities: List<Entity>)
}