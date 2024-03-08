package minutesock.ecs.system

import minutesock.ecs.*
import kotlin.reflect.KClass

abstract class System(
    var enabled: Boolean = true
) {

    init {
        validateAnnotations()
    }

    private fun validateAnnotations() {
        val annotations = this::class.annotations.map { it.annotationClass }
        if (annotations.isEmpty()) {
            throw SystemMissingAnnotationException(
                "A System class must be annotated with ${AllOfComponents::class.simpleName}, ${NoneOfComponents::class.simpleName}, or ${AnyComponents::class.simpleName}."
            )
        }

        val validAnnotations = listOf(AllOfComponents::class, NoneOfComponents::class, AnyComponents::class)
        val illegalAnnotations = annotations.filterNot { annotation: KClass<out Annotation> ->
            validAnnotations.contains(annotation)
        }

        if (illegalAnnotations.isNotEmpty()) {
            throw SystemIllegalAnnotationException(
                illegalAnnotationClasses = illegalAnnotations,
                clarifyingMessage = "Systems can only have ${AllOfComponents::class.simpleName}, ${NoneOfComponents::class.simpleName}, or ${AnyComponents::class.simpleName} annotations"
            )
        }

        if (annotations.size > 1 && annotations.contains(AnyComponents::class)) {
            throw SystemIncompatibleAnyComponentsAnnotationException()
        }
    }

    internal fun getFilteredEntities(entities: List<Entity>): List<Entity> {
        val annotations = this::class.annotations
        if (annotations.first() is AnyComponents) {
            return entities
        }
        var filteredEntities = entities.toMutableList()
        annotations.forEach { annotation: Annotation ->
            when (annotation) {
                is AnyComponents -> return entities
                is NoneOfComponents -> {
                    filteredEntities = filteredEntities.filterNot { entity: Entity ->
                        entity.components.keys.any { annotation.components.contains(it) }
                    }.toMutableList()
                }

                is AllOfComponents -> {
                    filteredEntities = filteredEntities.filter { entity: Entity ->
                        annotation.components.all { requiredComponent: KClass<out Component<*>> ->
                            entity.components.keys.contains(requiredComponent)
                        }
                    }.toMutableList()
                }
            }
        }
        return filteredEntities
    }
}