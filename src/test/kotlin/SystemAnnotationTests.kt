package minutesock

import minutesock.ecs.*
import minutesock.ecs.Any
import org.junit.Assert
import org.junit.Test

class SystemAnnotationTests {

    @Test
    fun anyAnnotationIsPresentWithOtherValidAnnotations() {
        @Any
        @NoneOf([ObstacleComponent::class])
        class TestSystem : System<TestSystem>() {
            override fun update(delta: Long, entities: List<Entity>, allEntities: List<Entity>) {
                TODO("Not yet implemented")
            }

        }
        try {
            val testSystem = TestSystem()
        } catch (e:Exception) {
            Assert.assertTrue(e is IllegalArgumentException)
            Assert.assertEquals(e.message, "System classes cannot have an ${kotlin.Any::class.simpleName} annotation with any other annotation. ${kotlin.Any::class.simpleName} must be the only annotation.")
        }
    }
}