package minutesock

import minutesock.ecs.Entity
import minutesock.ecs.EntityFactory
import org.junit.Assert
import org.junit.Test


class EntityComponentTests {

    @Test
    fun hasComponents() {
        val entity = Entity(
            id = 1,
            components = mutableSetOf(
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
            components = mutableSetOf(
                TeamComponent(team = Team.Team1)
            )
        )

        Assert.assertFalse(entity.hasComponents(TransformComponent::class))
    }

    @Test
    fun doesNotHaveComponents() {
        val entity = Entity(
            id = 1,
            components = mutableSetOf(
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
            components = mutableSetOf(
                TeamComponent(team = Team.Team2)
            )
        )

        val teamComponent: TeamComponent = entity.requireComponent()
        Assert.assertTrue(teamComponent.team == Team.Team2)
    }

    @Test
    fun addComponents() {
        val entity = EntityFactory().createEntity()
        Assert.assertEquals(false, entity.hasComponents(ObstacleComponent::class))
        Assert.assertEquals(false, entity.componentClasses.contains(ObstacleComponent::class))
        Assert.assertEquals(0, entity.components.size)
        entity.addComponents(ObstacleComponent(), TeamComponent(team = Team.Team1))
        Assert.assertEquals(true, entity.hasComponents(ObstacleComponent::class))
        Assert.assertEquals(true, entity.hasComponents(ObstacleComponent::class))
        Assert.assertEquals(true, entity.componentClasses.contains(ObstacleComponent::class))
        Assert.assertEquals(2, entity.components.size)
    }

    @Test
    fun removeComponent() {
        val entity = EntityFactory().createEntity(
            components = mutableSetOf(
                ObstacleComponent(),
                TeamComponent(team = Team.Team1),
                MovementComponent(pos = Vector3(1,1,1))
            )
        )
        Assert.assertEquals(3, entity.components.size)
        Assert.assertEquals(true, entity.hasComponents(TeamComponent::class))
        Assert.assertEquals(true, entity.componentClasses.contains(TeamComponent::class))
        entity.removeComponents(entity.requireComponent<TeamComponent>())
        Assert.assertEquals(2, entity.components.size)
        Assert.assertEquals(false, entity.hasComponents(TeamComponent::class))
        Assert.assertEquals(false, entity.componentClasses.contains(TeamComponent::class))
    }

    @Test
    fun removeComponentByClassType() {
        val entity = EntityFactory().createEntity(
            components = mutableSetOf(
                ObstacleComponent(),
                TeamComponent(team = Team.Team1),
                MovementComponent(pos = Vector3(1,1,1))
            )
        )
        Assert.assertEquals(3, entity.components.size)
        Assert.assertEquals(true, entity.hasComponents(TeamComponent::class))
        Assert.assertEquals(true, entity.componentClasses.contains(TeamComponent::class))
        entity.removeComponents(TeamComponent::class, MovementComponent::class)
        Assert.assertEquals(1, entity.components.size)
        Assert.assertEquals(false, entity.hasComponents(TeamComponent::class))
        Assert.assertEquals(false, entity.hasComponents(MovementComponent::class))
        Assert.assertEquals(false, entity.componentClasses.contains(TeamComponent::class))
        Assert.assertEquals(false, entity.componentClasses.contains(MovementComponent::class))
    }

    @Test
    fun addExistingComponentClassToEntity() {
        val entity = EntityFactory().createEntity(
            components = mutableSetOf(
                ObstacleComponent(),
            )
        )

        try {
            entity.addComponents(ObstacleComponent())
        } catch (e: Exception) {
            Assert.assertTrue(e is IllegalArgumentException)
            Assert.assertEquals("Entities cannot have duplicate component types.", e.message)
        }
        Assert.assertEquals(1, entity.components.size)
    }

    @Test
    fun addDuplicateComponentClasses() {
        val entity = EntityFactory().createEntity(
            components = mutableSetOf(
                TeamComponent(team = Team.Team1),
            )
        )

        try {
            entity.addComponents(ObstacleComponent(), ObstacleComponent())
        } catch (e: Exception) {
            Assert.assertTrue(e is IllegalArgumentException)
            Assert.assertEquals("Entities cannot have duplicate component types.", e.message)
        }
        Assert.assertEquals(1, entity.components.size)
    }
}
