package minutesock.ecs.system

import minutesock.ecs.Component
import kotlin.reflect.KClass

annotation class AllOfComponents(val components: Array<KClass<out Component<*>>>)
annotation class NoneOfComponents(val components: Array<KClass<out Component<*>>>)
annotation class AnyComponents
annotation class AllOfIterativeSystems(val systems: Array<KClass<out IterativeSystem>>)
