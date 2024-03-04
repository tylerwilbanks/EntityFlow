package com.minutesock.cardslam.ecs

import com.main.ecs.Component
import kotlin.reflect.KClass

annotation class AllOf(val components: Array<KClass<out Component<*>>>)
annotation class NoneOf(val components: Array<KClass<out Component<*>>>)
annotation class Any
