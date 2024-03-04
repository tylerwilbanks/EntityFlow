package minutesock

import minutesock.ecs.AnyComponents
import org.junit.Assert
import org.junit.Test

class SystemAnnotationTests {

    @Test
    fun anyAnnotationIsPresentWithOtherValidAnnotations() {
        try {
            SystemWithAnyAndOtherValidAnnotation()
        } catch (e:Exception) {
            Assert.assertTrue(e is IllegalArgumentException)
            Assert.assertEquals(e.message, "System classes cannot have an ${AnyComponents::class.simpleName} annotation with any other annotation. ${AnyComponents::class.simpleName} must be the only annotation.")
        }
    }
    @Test
    fun invalidAnnotation() {
        try {
            SystemWithInvalidAnnotation()
        } catch (e:Exception) {
            Assert.assertTrue(e is IllegalArgumentException)
            Assert.assertEquals(e.message, "Found illegal annotation(s) InvalidAnnotation\n" +
                    "Systems can only have AllOfComponents, NoneOfComponents, or AnyComponents annotations")
        }
    }
}