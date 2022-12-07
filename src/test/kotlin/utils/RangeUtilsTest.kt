package utils

import com.yonatankarp.adventofcode.utils.anyContainedIn
import com.yonatankarp.adventofcode.utils.containedIn
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RangeUtilsTest {

    @Test
    fun `containedIn should return true for range that contains another range`() {
        val range = 2..4
        val containingRange = 1..8
        assertTrue(range.containedIn(containingRange))
    }

    @Test
    fun `containedIn should return true when both ranges are equal`() {
        val range = 2..4
        assertTrue(range.containedIn(range))
    }

    @Test
    fun `containedIn should return false when range not fully contained`() {
        val range = 2..4
        val containingRange = 3..8
        assertFalse(range.containedIn(containingRange))
    }

    @Test
    fun `anyContainedIn should return true when at least one element is included in other range`() {
        val range = 2..4
        val containingRange = 3..8
        assertTrue(range.anyContainedIn(containingRange))
    }

    @Test
    fun `anyContainedIn should return true when both ranges are equal`() {
        val range = 2..4
        assertTrue(range.anyContainedIn(range))
    }

    @Test
    fun `anyContainedIn should return false when range do not overlap`() {
        val range = 2..4
        val containingRange = 6..8
        assertFalse(range.anyContainedIn(containingRange))
    }
}
