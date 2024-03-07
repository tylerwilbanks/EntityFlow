package minutesock

import minutesock.ecs.EntityDuplicateComponentException
import minutesock.ecs.EntityFactory
import org.junit.Assert
import org.junit.Test


class EntityComponentTests {

    @Test
    fun hasComponents() {
        val entity = EntityFactory().createEntity(
            components = mutableSetOf(
                TransformComponent(pos = Vector3(0, 0, 0)),
                TeamComponent(team = Team.Team1)
            )
        )

        Assert.assertTrue(entity.hasComponents(TransformComponent::class, TeamComponent::class))
    }

    @Test
    fun doesNotHaveComponent() {
        val entity = EntityFactory().createEntity(
            components = mutableSetOf(
                TeamComponent(team = Team.Team1)
            )
        )

        Assert.assertFalse(entity.hasComponents(TransformComponent::class))
    }

    @Test
    fun doesNotHaveComponents() {
        val entity = EntityFactory().createEntity(
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
        val entity = EntityFactory().createEntity(
            components = mutableSetOf(
                TeamComponent(team = Team.Team2)
            )
        )

        val teamComponent: TeamComponent = entity[TeamComponent::class]
        Assert.assertTrue(teamComponent.team == Team.Team2)
    }

    @Test
    fun addComponents() {
        val entity = EntityFactory().createEntity()
        Assert.assertEquals(false, entity.hasComponents(ObstacleComponent::class))
        Assert.assertEquals(0, entity.components.size)
        entity.addComponents(ObstacleComponent(), TeamComponent(team = Team.Team1))
        Assert.assertEquals(true, entity.hasComponents(ObstacleComponent::class))
        Assert.assertEquals(true, entity.hasComponents(ObstacleComponent::class))
        Assert.assertEquals(2, entity.components.size)
    }

    @Test
    fun removeComponent() {
        val entity = EntityFactory().createEntity(
            components = mutableSetOf(
                ObstacleComponent(),
                TeamComponent(team = Team.Team1),
                MovementComponent(pos = Vector3(1, 1, 1))
            )
        )
        Assert.assertEquals(3, entity.components.size)
        Assert.assertEquals(true, entity.hasComponents(TeamComponent::class))
        entity.removeComponents(entity[TeamComponent::class])
        Assert.assertEquals(2, entity.components.size)
        Assert.assertEquals(false, entity.hasComponents(TeamComponent::class))
    }

    @Test
    fun removeComponentByClassType() {
        val entity = EntityFactory().createEntity(
            components = mutableSetOf(
                ObstacleComponent(),
                TeamComponent(team = Team.Team1),
                MovementComponent(pos = Vector3(1, 1, 1))
            )
        )
        Assert.assertEquals(3, entity.components.size)
        Assert.assertEquals(true, entity.hasComponents(TeamComponent::class))
        entity.removeComponents(TeamComponent::class, MovementComponent::class)
        Assert.assertEquals(1, entity.components.size)
        Assert.assertEquals(false, entity.hasComponents(TeamComponent::class))
        Assert.assertEquals(false, entity.hasComponents(MovementComponent::class))
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
            Assert.assertTrue(e is EntityDuplicateComponentException)
            Assert.assertEquals("An entity cannot have duplicate components of the same type.", e.message)
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
            Assert.assertTrue(e is EntityDuplicateComponentException)
            Assert.assertEquals("An entity cannot have duplicate components of the same type.", e.message)
        }
        Assert.assertEquals(1, entity.components.size)
    }

    @Test
    fun requireComponent() {
        val entity = EntityFactory().createEntity(
            components = mutableSetOf(
                ObstacleComponent()
            )
        )

        entity[ObstacleComponent::class]
    }

    @Test
    fun requireComponentFal() {
        val entity = EntityFactory().createEntity(
            components = mutableSetOf(
                ObstacleComponent()
            )
        )

        try {
            val teamComponent = entity[TeamComponent::class]
        } catch (e: Exception) {
            Assert.assertTrue(e is java.lang.NullPointerException)
        }
    }
}
