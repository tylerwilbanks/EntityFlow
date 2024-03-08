package minutesock.ecs.engine

import minutesock.ecs.Entity
import minutesock.ecs.system.*
import kotlin.reflect.KClass

class ReactiveEngine(
    private val reactiveSystems: MutableList<ReactiveSystem> = mutableListOf()
) : Engine<ReactiveSystem>, IterativeSystemListener {

    init {
        SystemEventBus.addListener(this)
    }

    override fun addSystems(vararg systems: ReactiveSystem) {
        reactiveSystems.addAll(systems)
    }

    override fun onPreUpdate(event: SystemEvent.PreUpdate) {
        update(
            processInterest = ReactiveSystemProcessInterest.PreUpdate,
            fromSystem = event.fromSystem,
            entities = event.entities
        ) { system: ReactiveSystem, filteredEntities: List<Entity> ->
            system.onPreUpdate(event.fromSystem, event.delta, filteredEntities)
        }
    }

    override fun onPostUpdate(event: SystemEvent.PostUpdate) {
        update(
            processInterest = ReactiveSystemProcessInterest.PostUpdate,
            fromSystem = event.fromSystem,
            entities = event.entities
        ) { system: ReactiveSystem, filteredEntities: List<Entity> ->
            system.onPostUpdate(event.fromSystem, event.delta, filteredEntities)
        }
    }

    private fun update(
        processInterest: ReactiveSystemProcessInterest,
        fromSystem: KClass<out IterativeSystem>,
        entities: List<Entity>,
        onUpdate: (system: ReactiveSystem, filteredEntities: List<Entity>) -> Unit
    ) {
        for (system in reactiveSystems) {
            if (!system.enabled) continue

            if (system.processInterest.isInterested(processInterest) &&
                system.isInterestedInThisSystem(fromSystem)
            ) {
                onUpdate(system, system.getFilteredEntities(entities))
            }
        }
    }
}