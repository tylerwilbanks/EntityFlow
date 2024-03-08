package minutesock.ecs.engine

import minutesock.ecs.Entity
import minutesock.ecs.system.IterativeSystem
import minutesock.ecs.system.SystemEvent
import minutesock.ecs.system.SystemEventBus

class IterativeEngine(
    iterativeSystems: MutableList<IterativeSystem> = mutableListOf(),
) : Engine<IterativeSystem> {

    private val iterativeSystems: MutableList<IterativeSystem> =
        iterativeSystems.sortedWith(compareBy(nullsLast()) { it.sortOrder }).toMutableList()

    fun update(delta: Long, entities: List<Entity>) {
        for (system in iterativeSystems) {
            if (!system.enabled) continue
            val filteredEntities = system.getFilteredEntities(entities)
            SystemEventBus.onEvent(SystemEvent.PreUpdate(system::class, delta, entities))
            system.update(delta, filteredEntities)
            SystemEventBus.onEvent(SystemEvent.PostUpdate(system::class, delta, entities))
        }
    }

    override fun addSystems(vararg systems: IterativeSystem) {
        this.iterativeSystems.addAll(systems.sortedWith(compareBy(nullsLast()) { it.sortOrder }))
    }

    internal val systems get() = iterativeSystems
}
