package minutesock.ecs

import minutesock.ecs.engine.SimpleEngine
import java.lang.System as javaSystem

class EntityRunner(
    private val engine: Engine = SimpleEngine(),
    val tickSpeed: Int = 15 // in milliseconds
) {

    private var lastDeltaTime = javaSystem.currentTimeMillis()

    private val delta: Long get() {
        val currentTime = javaSystem.currentTimeMillis()
        val delta = currentTime - lastDeltaTime
        lastDeltaTime = currentTime
        return delta
    }

    private val tickTimeElapsed: Boolean get() =
        javaSystem.currentTimeMillis() - lastDeltaTime >= tickSpeed

    fun createEntity(components: MutableList<Component<*>>, entityId: Int = -1): Entity {
        return engine.createEntity(components, entityId)
    }

    fun addSystems(vararg system: System<*>) {
        engine.addSystems(*system)
    }

    fun update() {
        if (tickTimeElapsed) {
            engine.update(delta)
        }
    }
}
