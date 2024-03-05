package minutesock

import minutesock.ecs.Entity
import org.junit.Assert
import org.junit.Test


class EntityComponentTests {

    @Test
    fun hasComponents() {
        val entity = Entity(
            id = 1,
            components = mutableListOf(
                TransformComponent(pos = Vector3(0, 0, 0)),
                TeamComponent(team = Team.Team1)
            )
        )

        Assert.assertTrue(entity.hasComponents(TransformComponent::class, TeamComponent::class))
    }

    @Test
    fun doesNotHaveComponent() {
        val entity = Entity(
            id = 1,
            components = mutableListOf(
                TeamComponent(team = Team.Team1)
            )
        )

        Assert.assertFalse(entity.hasComponents(TransformComponent::class))
    }

    @Test
    fun doesNotHaveComponents() {
        val entity = Entity(
            id = 1,
            components = mutableListOf(
                TeamComponent(team = Team.Team1)
            )
        )

        Assert.assertFalse(
            entity.hasComponents(
                TransformComponent::class,
                ObstacleComponent::class,
                MovementComponent::class
            )
        )
    }

    @Test
    fun getComponent() {
        val entity = Entity(
            id = 1,
            components = mutableListOf(
                TeamComponent(team = Team.Team2)
            )
        )

        val teamComponent: TeamComponent = entity.requireComponent()
        Assert.assertTrue(teamComponent.team == Team.Team2)
    }
}
