package minutesock.ecs

import minutesock.ecs.engine.SimpleEngine
import org.example.minutesock.ecs.EntityCreationConfig
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

    fun createEntities(vararg entityCreationConfig: EntityCreationConfig): List<Entity> {
        val entities = mutableListOf<Entity>()
        entityCreationConfig.forEach {
            entities.add(engine.createEntity(it.components, it.entityId))
        }
        return entities
    }

    fun addSystems(vararg system: System) {
        engine.addSystems(*system)
    }

    fun update(delta: Long?) {
        if (tickTimeElapsed) {
            engine.update(delta ?: this.delta)
        }
    }

    fun forceUpdate(delta: Long?) {
        engine.update(delta ?: this.delta)
    }
}
