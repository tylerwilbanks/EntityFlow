package minutesock

import minutesock.ecs.engine.IterativeEngine
import org.junit.Assert
import org.junit.Test

class IterativeSystemSortOrderTests {

    @Test
    fun sortOrderCorrect() {
        val sys1 = IterativeSystem1(1)
        val sys2 = IterativeSystem2(2)
        val sys3 = IterativeSystem3(3)


        val engine = IterativeEngine()
        engine.addSystems(
            sys3, sys2, sys1
        )

        Assert.assertEquals(sys1, engine.systems.first())
    }

    @Test
    fun sortOrderCorrectWithNulls() {
        val sys1 = IterativeSystem1(1)
        val sys2 = IterativeSystem2(null)
        val sys3 = IterativeSystem3(null)


        val engine = IterativeEngine()
        engine.addSystems(
            sys3, sys2, sys1
        )

        Assert.assertEquals(sys1, engine.systems.first())
    }

    // todo-tyler write test to sort in IterativeEngine constructor
}