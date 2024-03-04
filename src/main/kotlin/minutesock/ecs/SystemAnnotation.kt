package minutesock.ecs

import kotlin.reflect.KClass

annotation class AllOfComponents(val components: Array<KClass<out Component<*>>>)
annotation class NoneOfComponents(val components: Array<KClass<out Component<*>>>)
annotation class AnyComponents
