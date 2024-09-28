package io.features.home.data

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
    fun get_value_list_of_map_entries() {
        val map: Map<String, String> = mapOf(
            "one" to "cricket",
            "two" to "badminton",
            "three" to "football"
        )

        assertEquals(map.values.toList(), listOf("cricket","badminton","football"))
    }
}