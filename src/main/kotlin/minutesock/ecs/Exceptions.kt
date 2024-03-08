package minutesock.ecs

import minutesock.ecs.system.AnyComponents
import kotlin.reflect.KClass

class EntityDuplicateComponentException(override val message: String = "An entity cannot have duplicate components of the same type.") :
    Exception()

class SystemMissingAnnotationException(
    override val message: String
) : Exception()

class SystemIllegalAnnotationException(
    private val illegalAnnotationClasses: List<KClass<*>>,
    private val clarifyingMessage: String,
    override val message: String = "Found illegal annotation(s) ${
        illegalAnnotationClasses.map { it.simpleName }.joinToString(",")
    }\n" + clarifyingMessage
) : Exception()

class SystemIncompatibleAnyComponentsAnnotationException(
    override val message: String =
        "System classes cannot have an ${AnyComponents::class.simpleName} annotation with any other annotation. ${AnyComponents::class.simpleName} must be the only annotation."
) : Exception()