package minutesock.ecs

import kotlin.reflect.KClass


abstract class System {

    init {
        validateAnnotations()
    }

    private fun validateAnnotations() {
        val annotations = this::class.annotations.map { it.annotationClass }
        if (annotations.isEmpty()) {
            throw IllegalArgumentException("The System class must be annotated with ${AllOfComponents::class.simpleName}, ${NoneOfComponents::class.simpleName}, or ${AnyComponents::class.simpleName}.")
        }

        val validAnnotations = listOf(AllOfComponents::class, NoneOfComponents::class, AnyComponents::class)
        val illegalAnnotations = annotations.filterNot { annotation: KClass<out Annotation> ->
            validAnnotations.contains(annotation)
        }
        if (illegalAnnotations.isNotEmpty()) {
            throw IllegalArgumentException(
                "Found illegal annotation(s) ${annotations.map { it.simpleName }.joinToString(",")}\n" +
                        "Systems can only have ${AllOfComponents::class.simpleName}, ${NoneOfComponents::class.simpleName}, or ${AnyComponents::class.simpleName} annotations"
            )
        }
        if (annotations.size > 1 && annotations.contains(AnyComponents::class)) {
            throw IllegalArgumentException("System classes cannot have an ${AnyComponents::class.simpleName} annotation with any other annotation. ${AnyComponents::class.simpleName} must be the only annotation.")
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
                        entity.componentClasses.any { annotation.components.contains(it) }
                    }.toMutableList()
                }

                is AllOfComponents -> {
                    filteredEntities = filteredEntities.filter { entity: Entity ->
                        annotation.components.all { requiredComponent: KClass<out Component<*>> ->
                            entity.componentClasses.contains(requiredComponent)
                        }
                    }.toMutableList()
                }
            }
        }
        return filteredEntities
    }

    fun preUpdate(delta: Long, entities: List<Entity>, allEntities: List<Entity>) = Unit
    abstract fun update(delta: Long, entities: List<Entity>, allEntities: List<Entity>)
    fun postUpdate(delta: Long, entities: List<Entity>, allEntities: List<Entity>) = Unit
}
