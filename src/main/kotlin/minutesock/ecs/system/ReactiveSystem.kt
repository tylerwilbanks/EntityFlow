package minutesock.ecs.system

import minutesock.ecs.Entity
import minutesock.ecs.SystemIllegalAnnotationException
import minutesock.ecs.SystemMissingAnnotationException
import kotlin.reflect.KClass

abstract class ReactiveSystem(
    val processInterest: ReactiveSystemProcessInterest,
    enabled: Boolean = true
) : System(enabled) {

    private val systemInterests: List<KClass<out IterativeSystem>>

    init {
        systemInterests = validateReactiveSystemAnnotations()
    }

    private fun validateReactiveSystemAnnotations(): List<KClass<out IterativeSystem>> {
        val annotations = this::class.annotations
        if (annotations.isEmpty()) {
            throw SystemMissingAnnotationException(
                "A reactive system class must be annotated with ${AllOfIterativeSystems::class.simpleName}."
            )
        }

        if (annotations.size > 1) {
            throw SystemMissingAnnotationException(
                "A ${ReactiveSystem::class.simpleName} may only have 1 ${AllOfIterativeSystems::class.simpleName} annotation."
            )
        }

        val illegalAnnotations = annotations.filter { annotation: Annotation ->
            annotation::class == AllOfIterativeSystems::class
        }
        if (illegalAnnotations.isNotEmpty()) {
            throw SystemIllegalAnnotationException(
                illegalAnnotationClasses = illegalAnnotations.map { it::class },
                clarifyingMessage = "A ${ReactiveSystem::class.simpleName} may only have 1 ${AllOfIterativeSystems::class.simpleName} annotation."
            )
        }

        val a = this::class.annotations.find { it is AllOfIterativeSystems } as AllOfIterativeSystems
        if (a.systems.isEmpty()) {
            throw SystemMissingAnnotationException(
                "A ${AllOfIterativeSystems::class.simpleName} annotation must define at least 1 ${IterativeSystem::class.simpleName} to react to."
            )
        }
        return a.systems.toList()
    }

    internal fun isInterestedInThisSystem(fromSystem: KClass<out IterativeSystem>): Boolean {
        return systemInterests.contains(fromSystem)
    }

    abstract fun onPreUpdate(fromSystem: KClass<out IterativeSystem>, delta: Long, entities: List<Entity>)
    abstract fun onPostUpdate(fromSystem: KClass<out IterativeSystem>, delta: Long, entities: List<Entity>)
}

enum class ReactiveSystemProcessInterest {
    PreUpdate,
    PostUpdate,
    All;

    fun isInterested(processInterest: ReactiveSystemProcessInterest): Boolean {
        return when (processInterest) {
            PreUpdate -> this == All || this == PreUpdate
            PostUpdate -> this == All || this == PostUpdate
            All -> this == All
        }
    }
}