package minutesock

import minutesock.ecs.*
import org.junit.Assert
import org.junit.Test

class IterativeSystemAnnotationTests {

    @Test
    fun anyAnnotationIsPresentWithOtherValidAnnotations() {
        try {
            IterativeSystemWithAnyAndOtherValidAnnotation()
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
            IterativeSystemWithInvalidAnnotation()
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
            IterativeSystemWithNoAnnotations()
        } catch (e: Exception) {
            Assert.assertTrue(e is SystemMissingAnnotationException)
            Assert.assertEquals(
                e.message,
                "A System class must be annotated with ${AllOfComponents::class.simpleName}, ${NoneOfComponents::class.simpleName}, or ${AnyComponents::class.simpleName}."
            )
        }
    }
}