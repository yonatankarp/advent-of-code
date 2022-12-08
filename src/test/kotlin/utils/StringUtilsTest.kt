package utils

import com.yonatankarp.adventofcode.utils.hasDuplicates
import com.yonatankarp.adventofcode.utils.padEndWith
import com.yonatankarp.adventofcode.utils.padStartWith
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class StringUtilsTest {
    @Test
    fun `should return true for string with duplicated characters and ignored case`() {
        assertTrue("yoNatAn".hasDuplicates())
    }

    @Test
    fun `should return false for string with duplicated characters and case sensitive`() {
        assertFalse("yoNatAn".hasDuplicates(ignoreCases = false))
    }

    @Test
    fun `should return false for string with no duplications`() {
        assertFalse("Corn".hasDuplicates(ignoreCases = false))
    }

    @Test
    fun `should pad the beginning of a string`() {
        assertEquals("---ABC", "ABC".padStartWith(3, '-'))
    }

    @Test
    fun `should pad the beginning of a string with none visible char`() {
        assertEquals("\tABC", "ABC".padStartWith(1, '\t'))
    }

    @Test
    fun `should pad the end of a string`() {
        assertEquals("ABC---", "ABC".padEndWith(3, '-'))
    }

    @Test
    fun `should pad the end of a string with none visible char`() {
        assertEquals("ABC\t", "ABC".padEndWith(1, '\t'))
    }
}
