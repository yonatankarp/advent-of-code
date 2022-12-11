package utils

import com.yonatankarp.adventofcode.utils.CyclicLinkedList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CyclicLinkedListTest {

    @Test
    fun `should return null for get first when list is empty`() {
        assertNull(CyclicLinkedList<String>().first)
    }

    @Test
    fun `should return first element correctly when only 1 element in the list`() {
        val list = CyclicLinkedList<String>()
        list.add("Kotlin")
        assertEquals("Kotlin", list.first)
    }

    @Test
    fun `should return first element correctly when there are multiple items in list`() {
        val list = CyclicLinkedList<String>()
        list.add("Kotlin")
        list.add("Java")
        list.add("Python")
        assertEquals("Kotlin", list.first)
    }

    @Test
    fun `should return null for get last when list is empty`() {
        assertNull(CyclicLinkedList<String>().last)
    }

    @Test
    fun `should replace head when adding at position zero`() {
        val list = CyclicLinkedList<String>()
        list.add("Kotlin")
        list.add(0, "Python")
        assertEquals("Python", list.first)
        assertEquals("Kotlin", list.last)
    }

    @Test
    fun `should get elements from the list correctly`() {
        val list = CyclicLinkedList<String>()
        list.addAll(0, listOf("Kotlin", "Java", "C#", "C", "C++"))
        assertEquals("Kotlin", list[0])
        assertEquals("Java", list[1])
        assertEquals("C#", list[2])
        assertEquals("C", list[3])
        assertEquals("C++", list[4])
    }

    @Test
    fun `should set elements in the list correctly`() {
        val list = CyclicLinkedList<String>()
        list.addAll(0, listOf("Kotlin", "Java", "C#", "C", "C++"))

        list[0] = "Edited Kotlin"
        list[1] = "Edited Java"
        list[2] = "Edited C#"
        list[3] = "Edited C"
        list[4] = "Edited C++"

        assertEquals("Edited Kotlin", list[0])
        assertEquals("Edited Java", list[1])
        assertEquals("Edited C#", list[2])
        assertEquals("Edited C", list[3])
        assertEquals("Edited C++", list[4])
    }

    @Test
    fun `should remove first element successfully`() {
        val list = CyclicLinkedList<String>()
        list.addAll(0, listOf("Kotlin", "Java", "C#", "C", "C++"))
        list.removeFirst()
        assertEquals("Java", list.first)
    }

    @Test
    fun `should not do anything when removing first element of empty list`() {
        assertEquals(Unit, CyclicLinkedList<String>().removeFirst())
    }

    @Test
    fun `should remove last element successfully`() {
        val list = CyclicLinkedList<String>()
        list.addAll(0, listOf("Kotlin", "Java", "C#", "C", "C++"))
        list.removeLast()
        assertEquals("C", list.last)
    }

    @Test
    fun `should not do anything when removing last element of empty list`() {
        assertEquals(Unit, CyclicLinkedList<String>().removeLast())
    }

    @Test
    fun `should remove value from list successfully`() {
        val list = CyclicLinkedList<String>()
        list.addAll(0, listOf("Kotlin", "Java", "C#", "C", "C++"))

        list.remove("Java")
        assertTrue("Java" !in list)
    }

    @Test
    fun `should answer true when element is in list`() {
        val list = CyclicLinkedList<String>()
        val elements = listOf("Kotlin", "Java", "C#", "C", "C++")
        list.addAll(0, elements)
        elements.forEach { assertTrue(it in list) }
    }

    @Test
    fun `should answer false when elements are not in list`() {
        val list = CyclicLinkedList<String>()
        val elements = listOf("Kotlin", "Java", "C#", "C", "C++")
        list.addAll(0, emptyList())
        elements.forEach { assertFalse(it in list) }
    }

    @Test
    fun `should add all at the beginning of the list`() {
        val list = CyclicLinkedList<String>()
        list.addAll(0, listOf("Kotlin", "Java"))
        assertEquals("Kotlin", list[0])
        assertEquals("Java", list[1])
    }

    @Test
    fun `should add all in specific index`() {
        val list = CyclicLinkedList<String>()
        list.add("Kotlin")
        list.add("Java")
        list.add("C#")
        list.add("C")
        list.add("C++")

        list.addAll(2, listOf("Python", "Golang"))

        assertEquals("Kotlin", list[0])
        assertEquals("Java", list[1])
        assertEquals("Python", list[2])
        assertEquals("Golang", list[3])
        assertEquals("C#", list[4])
        assertEquals("C", list[5])
        assertEquals("C++", list[6])
    }
}
