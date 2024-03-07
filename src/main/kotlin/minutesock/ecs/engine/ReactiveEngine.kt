package minutesock.ecs.engine

import minutesock.ecs.system.ReactiveSystem

class ReactiveEngine(
    private val reactiveSystems: MutableList<ReactiveSystem> = mutableListOf()
) : Engine<ReactiveSystem> {

    override fun addSystems(vararg system: ReactiveSystem) {
        reactiveSystems.addAll(system)
    }
}