package minutesock

import minutesock.ecs.EntityCreationConfig
import minutesock.ecs.EntityRunner
import org.junit.Assert
import org.junit.Test


class SystemFilteringTests {

    @Test
    fun allOfFilteringIsCorrect() {
        val runner = EntityRunner(
            tickSpeed = 10_000
        )
        val entities = runner.createEntities(
            EntityCreationConfig(
                components = mutableSetOf(
                    MovementComponent(pos = Vector3(1, 1, 1))
                )
            ),
            EntityCreationConfig(
                components = mutableSetOf(
                    ObstacleComponent()
                )
            ),
            EntityCreationConfig(
                components = mutableSetOf(
                    ObstacleComponent()
                )
            )
        )
        val movementSystem = MovementSystem()
        Assert.assertEquals(1, movementSystem.getFilteredEntities(entities).size)
    }

    @Test
    fun noneOfFilteringIsCorrect() {
        val runner = EntityRunner(
            tickSpeed = 10_000
        )
        val entities = runner.createEntities(
            EntityCreationConfig(
                components = mutableSetOf(
                    MovementComponent(pos = Vector3(1, 1, 1))
                )
            ),
            EntityCreationConfig(
                components = mutableSetOf(
                    ObstacleComponent(),
                    TransformComponent(pos = Vector3(1, 1, 1))
                )
            ),
            EntityCreationConfig(
                components = mutableSetOf(
                    ObstacleComponent()
                )
            )
        )
        val teleportSystem = TeleportSystem()
        Assert.assertEquals(1, teleportSystem.getFilteredEntities(entities).size)
    }

    @Test
    fun anySystemDoesNotFilter() {
        val runner = EntityRunner(
            tickSpeed = 10_000
        )
        val entities = runner.createEntities(
            EntityCreationConfig(
                components = mutableSetOf(
                    MovementComponent(pos = Vector3(1, 1, 1))
                )
            ),
            EntityCreationConfig(
                components = mutableSetOf(
                    ObstacleComponent(),
                    TransformComponent(pos = Vector3(1, 1, 1))
                )
            ),
            EntityCreationConfig(
                components = mutableSetOf(
                    ObstacleComponent()
                )
            )
        )
        val whateverSystem = WhateverSystem()
        Assert.assertEquals(3, whateverSystem.getFilteredEntities(entities).size)
    }
}