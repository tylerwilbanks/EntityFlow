package minutesock

import minutesock.ecs.Component

internal enum class ComponentId {
    Team,
    Transform,
    Obstacle,
    Movement
}

internal enum class Team {
    Team1,
    Team2
}

internal data class TeamComponent(override val id: Int = ComponentId.Team.ordinal, val team: Team) :
    Component<TeamComponent>

internal data class TransformComponent(override val id: Int = ComponentId.Transform.ordinal, val pos: Vector3) :
    Component<TransformComponent>

internal data class Vector3(val x: Int, val y: Int, val z: Int)
internal data class ObstacleComponent(override val id: Int = ComponentId.Obstacle.ordinal) :
    Component<ObstacleComponent>

internal data class MovementComponent(override val id: Int = ComponentId.Movement.ordinal, val pos: Vector3) :
    Component<MovementComponent>

