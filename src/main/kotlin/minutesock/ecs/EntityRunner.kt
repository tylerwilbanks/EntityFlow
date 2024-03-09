package minutesock.ecs

import minutesock.ecs.engine.IterativeEngine
import minutesock.ecs.engine.ReactiveEngine
import minutesock.ecs.system.IterativeSystem
import minutesock.ecs.system.ReactiveSystem
import java.lang.System as javaSystem

class EntityRunner(
    val tickSpeed: Int = 15, // in milliseconds,
    private val iterativeEngine: IterativeEngine = IterativeEngine(),
    private val reactiveEngine: ReactiveEngine = ReactiveEngine(),
) {
    private var lastDeltaTime = javaSystem.currentTimeMillis()

    private val delta: Long
        get() {
            val currentTime = javaSystem.currentTimeMillis()
            val delta = currentTime - lastDeltaTime
            lastDeltaTime = currentTime
            return delta
        }

    private val tickTimeElapsed: Boolean
        get() =
            javaSystem.currentTimeMillis() - lastDeltaTime >= tickSpeed

    fun addIterativeSystems(vararg iterativeSystem: IterativeSystem) {
        iterativeEngine.addSystems(*iterativeSystem)
    }

    fun addReactiveSystems(vararg reactiveSystem: ReactiveSystem) {
        reactiveEngine.addSystems(*reactiveSystem)
    }

    fun managedUpdate() {
        if (tickTimeElapsed) {
            iterativeEngine.update(delta, EntityWorld.entities)
        }
    }

    fun update(delta: Long) {
        iterativeEngine.update(delta, EntityWorld.entities)
    }
}
