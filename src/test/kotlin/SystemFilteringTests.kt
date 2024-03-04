package minutesock

import minutesock.ecs.EntityRunner
import org.junit.Test


class SystemFilteringTests {

    @Test
    fun asdf() {
        val runner = EntityRunner(
            tickSpeed = 1
        )
        runner.createEntity(
            components = mutableListOf(
                ObstacleComponent(1)
            )
        )
    }
}