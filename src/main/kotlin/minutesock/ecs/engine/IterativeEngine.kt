package minutesock.ecs.engine

import minutesock.ecs.*
import minutesock.ecs.system.IterativeSystem

class IterativeEngine(
    private val iterativeSystems: MutableList<IterativeSystem> = mutableListOf(),
) : Engine<IterativeSystem> {

    fun update(delta: Long, entities: List<Entity>) {
        for (system in iterativeSystems) {
            if (!system.enabled) continue
            val filteredEntities = system.getFilteredEntities(entities)
            // todo-tyler notify reactive engine pre-update
            system.update(delta, filteredEntities)
            // todo-tyler notify reactive engine post-update
        }
    }

    override fun addSystems(vararg system: IterativeSystem) {
        this.iterativeSystems.addAll(system)
    }
}
