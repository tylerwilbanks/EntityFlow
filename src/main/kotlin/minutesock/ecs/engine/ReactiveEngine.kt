package minutesock.ecs.engine

import minutesock.ecs.system.*

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
        reactiveSystems.forEach { system: ReactiveSystem ->
            if (system.processInterest.isInterested(ReactiveSystemProcessInterest.PreUpdate) && system.isInterestedInThisSystem(
                    event.fromSystem
                )
            ) {
                val filteredEntities = system.getFilteredEntities(event.entities)
                system.onPreUpdate(event.fromSystem, event.delta, filteredEntities)
            }
        }
    }

    override fun onPostUpdate(event: SystemEvent.PostUpdate) {
        reactiveSystems.forEach { system: ReactiveSystem ->
            if (system.processInterest.isInterested(ReactiveSystemProcessInterest.PostUpdate) && system.isInterestedInThisSystem(
                    event.fromSystem
                )
            ) {
                val filteredEntities = system.getFilteredEntities(event.entities)
                system.onPostUpdate(event.fromSystem, event.delta, filteredEntities)
            }
        }
    }
}