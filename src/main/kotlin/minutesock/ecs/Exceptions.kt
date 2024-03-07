package minutesock.ecs

import minutesock.ecs.system.AllOfComponents
import minutesock.ecs.system.AnyComponents
import minutesock.ecs.system.NoneOfComponents
import kotlin.reflect.KClass

class EntityDuplicateComponentException(override val message: String = "An entity cannot have duplicate components of the same type.") :
    Exception()

class SystemMissingAnnotationException(
    override val message: String =
        "A System class must be annotated with ${AllOfComponents::class.simpleName}, ${NoneOfComponents::class.simpleName}, or ${AnyComponents::class.simpleName}."
) : Exception()

class SystemIllegalAnnotationException(
    private val illegalAnnotationClasses: List<KClass<*>>,
    override val message: String = "Found illegal annotation(s) ${
        illegalAnnotationClasses.map { it.simpleName }.joinToString(",")
    }\n" +
            "Systems can only have ${AllOfComponents::class.simpleName}, ${NoneOfComponents::class.simpleName}, or ${AnyComponents::class.simpleName} annotations"
) : Exception()

class SystemIncompatibleAnyComponentsAnnotationException(
    override val message: String =
        "System classes cannot have an ${AnyComponents::class.simpleName} annotation with any other annotation. ${AnyComponents::class.simpleName} must be the only annotation."
) : Exception()