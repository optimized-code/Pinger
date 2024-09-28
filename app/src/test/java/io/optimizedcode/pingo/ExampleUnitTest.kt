package io.optimizedcode.pingo

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun verify_env_variable(){
        val result = System.getenv("PATH")?.contains("""/sdk/platform-tools""", ignoreCase = true)
        assertEquals(true, result)
    }
}