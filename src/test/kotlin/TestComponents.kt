package minutesock

import minutesock.ecs.Component

internal data class TeamComponent(override val id: Int, val team: Team) : Component<TeamComponent>

internal enum class Team {
    Team1,
    Team2
}

internal data class TransformComponent(override val id: Int, val pos: Vector3) : Component<TransformComponent>

internal data class Vector3(val x: Int, val y: Int, val z: Int)

internal data class ObstacleComponent(override val id: Int) : Component<ObstacleComponent>
internal data class MovementComponent(override val id: Int, val pos: Vector3) : Component<MovementComponent>