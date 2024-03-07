package minutesock

import minutesock.ecs.SystemIllegalAnnotationException
import minutesock.ecs.SystemIncompatibleAnyComponentsAnnotationException
import minutesock.ecs.SystemMissingAnnotationException
import minutesock.ecs.system.AllOfComponents
import minutesock.ecs.system.AnyComponents
import minutesock.ecs.system.NoneOfComponents
import org.junit.Assert
import org.junit.Test

class MovementSystemAnnotationTests {

    @Test
    fun anyAnnotationIsPresentWithOtherValidAnnotations() {
        try {
            SystemWithAnyAndOtherValidAnnotation()
        } catch (e: Exception) {
            Assert.assertTrue(e is SystemIncompatibleAnyComponentsAnnotationException)
            Assert.assertEquals(
                e.message,
                "System classes cannot have an ${AnyComponents::class.simpleName} annotation with any other annotation. ${AnyComponents::class.simpleName} must be the only annotation."
            )
        }
    }

    @Test
    fun invalidAnnotation() {
        try {
            SystemWithInvalidAnnotation()
        } catch (e: Exception) {
            Assert.assertTrue(e is SystemIllegalAnnotationException)
            Assert.assertEquals(
                e.message, "Found illegal annotation(s) InvalidAnnotation\n" +
                        "Systems can only have AllOfComponents, NoneOfComponents, or AnyComponents annotations"
            )
        }
    }

    @Test
    fun systemHasNoAnnotation() {
        try {
            SystemWithNoAnnotations()
        } catch (e: Exception) {
            Assert.assertTrue(e is SystemMissingAnnotationException)
            Assert.assertEquals(
                e.message,
                "A System class must be annotated with ${AllOfComponents::class.simpleName}, ${NoneOfComponents::class.simpleName}, or ${AnyComponents::class.simpleName}."
            )
        }
    }
}