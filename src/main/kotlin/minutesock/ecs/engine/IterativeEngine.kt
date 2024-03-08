package minutesock.ecs.engine

import minutesock.ecs.Entity
import minutesock.ecs.system.IterativeSystem
import minutesock.ecs.system.SystemEvent
import minutesock.ecs.system.SystemEventBus

class IterativeEngine(
    iterativeSystems: MutableList<IterativeSystem> = mutableListOf(),
    private val comparator: Comparator<IterativeSystem> = compareBy(nullsLast()) { it.sortOrder }
) : Engine<IterativeSystem> {

    private val iterativeSystems: MutableList<IterativeSystem> =
        iterativeSystems.sortedWith(comparator).toMutableList()

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
        this.iterativeSystems.addAll(systems.sortedWith(comparator))
    }

    internal val systems get() = iterativeSystems
}
