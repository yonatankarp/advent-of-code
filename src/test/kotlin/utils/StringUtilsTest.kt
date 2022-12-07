package utils

import com.yonatankarp.adventofcode.utils.hasDuplicates
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
}
