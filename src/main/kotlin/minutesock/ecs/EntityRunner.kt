package minutesock.ecs

import minutesock.ecs.engine.IterativeEngine
import minutesock.ecs.engine.ReactiveEngine
import minutesock.ecs.system.IterativeSystem
import minutesock.ecs.system.ReactiveSystem
import minutesock.ecs.system.SystemEventBus
import java.lang.System as javaSystem

class EntityRunner(
    val tickSpeed: Int = 15, // in milliseconds,
    private val entityFactory: EntityFactory = EntityFactory(),
    private val iterativeEngine: IterativeEngine = IterativeEngine(),
    private val reactiveEngine: ReactiveEngine = ReactiveEngine(),
    private val entities: MutableList<Entity> = mutableListOf()
) {

    init {
        SystemEventBus.clearListeners()
    }

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

    fun createEntity(components: MutableSet<Component<*>>, entityId: Int = -1): Entity {
        return entityFactory.createEntity(
            entityId = entityId,
            components = components,
        ).also { entity ->
            entities += entity
        }
    }

    fun createEntities(vararg entityCreationConfig: EntityCreationConfig): List<Entity> {
        val entities = mutableListOf<Entity>()
        entityCreationConfig.forEach { config: EntityCreationConfig ->
            entities.add(createEntity(config.components, config.entityId))
        }
        return entities
    }

    fun addIterativeSystems(vararg iterativeSystem: IterativeSystem) {
        iterativeEngine.addSystems(*iterativeSystem)
    }

    fun addReactiveSystems(vararg reactiveSystem: ReactiveSystem) {
        reactiveEngine.addSystems(*reactiveSystem)
    }

    fun managedUpdate() {
        if (tickTimeElapsed) {
            iterativeEngine.update(delta, entities)
        }
    }

    fun update(delta: Long) {
        iterativeEngine.update(delta, entities)
    }
}
