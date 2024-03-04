package minutesock.ecs

import kotlin.reflect.KClass
import kotlin.reflect.KProperty1


abstract class System<T> {

    init {
        validateAnnotations()
    }

    private fun validateAnnotations() {
        val annotations = this::class.annotations.map { it.annotationClass }
        if (annotations.isEmpty()) {
            throw IllegalArgumentException("The System class must be annotated with ${AllOf::class.simpleName}, ${NoneOf::class.simpleName}, or ${Any::class.simpleName}.")
        }

        val validAnnotations = listOf(AllOf::class, NoneOf::class, Any::class)
        val illegalAnnotations = annotations.filterNot { annotation: KClass<out Annotation> ->
            validAnnotations.contains(annotation)
        }
        if (illegalAnnotations.isNotEmpty()) {
            throw IllegalArgumentException("Found illegal annotation(s) ${annotations.map { it.simpleName }.joinToString(",")}\n" +
                    "Systems can only have ${AllOf::class.simpleName}, ${NoneOf::class.simpleName}, or ${Any::class.simpleName} annotations")
        }
        if (annotations.size > 1 && annotations.any { it == Any::class }) {
            throw IllegalArgumentException("System classes cannot have an ${Any::class.simpleName} annotation with any other annotation. ${Any::class.simpleName} must be the only annotation.")
        }
    }

    fun getFilteredEntities(entities: List<Entity>): List<Entity> {
        val annotations = this::class::annotations.annotations
        if (annotations.first() is Any) {
            return entities
        }
        val filteredEntities = entities.toMutableList()
        annotations.forEach { annotation: Annotation ->
            when (annotation) {
                is Any -> return entities
                is NoneOf -> {
                    filteredEntities.filter { entity: Entity ->
                        entity.componentClasses.any { annotation.components.contains(it) }
                    }
                }
                is AllOf -> {
                    filteredEntities.filterNot { entity: Entity ->
                        entity.componentClasses.any { annotation.components.contains(it) }
                    }
                }
            }
        }
        return filteredEntities
    }

    fun preUpdate(delta: Long, entities: List<Entity>, allEntities: List<Entity>) = Unit
    abstract fun update(delta: Long, entities: List<Entity>, allEntities: List<Entity>)
    fun postUpdate(delta: Long, entities: List<Entity>, allEntities: List<Entity>) = Unit
}
