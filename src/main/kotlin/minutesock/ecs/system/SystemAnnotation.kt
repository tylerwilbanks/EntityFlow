package minutesock.ecs.system

import minutesock.ecs.Component
import kotlin.reflect.KClass

annotation class AllOfComponents(val components: Array<KClass<out Component<*>>>)
annotation class NoneOfComponents(val components: Array<KClass<out Component<*>>>)
annotation class AnyComponents

// todo-tyler maybe create some annotation classes for reactive systems
