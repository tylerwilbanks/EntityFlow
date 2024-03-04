package com.main.ecs

import com.minutesock.cardslam.ecs.AllOf
import com.minutesock.cardslam.ecs.Any
import com.minutesock.cardslam.ecs.NoneOf


abstract class System<T> {

    init {
        validateSystemAnnotation()
    }

    private fun validateSystemAnnotation() {
        val annotations = this::class.annotations
        if (annotations.isEmpty()) {
            throw IllegalArgumentException("The System class must be annotated with AllOf, NoneOf or Any.")
        }

        val validAnnnotaions = listOf(AllOf::class, NoneOf::class, Any::class)
        // todo-tyler this is wrong and needs to be dealt with
        val containsUnknownAnnotations = annotations.all { annotation: Annotation ->
            validAnnnotaions.any {
                it::class == annotation::class
            }
        }
//        when (annotations) {
//            is AllOf -> Unit
//            is NoneOf -> Unit
//        }
    }

    val familyId: Int get() = -1
    fun filterByFamily(entities: List<Entity>): List<Entity> = entities.filter { it.familyId == familyId }
    fun preUpdate(delta: Long, entities: List<Entity>, allEntities: List<Entity>) = Unit
    abstract fun update(delta: Long, entities: List<Entity>, allEntities: List<Entity>)
    fun postUpdate(delta: Long, entities: List<Entity>, allEntities: List<Entity>) = Unit
}
