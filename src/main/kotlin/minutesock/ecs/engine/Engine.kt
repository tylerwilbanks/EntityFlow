package minutesock.ecs.engine

import minutesock.ecs.system.System

interface Engine<T : System> {
    fun addSystems(vararg system: T)
}

