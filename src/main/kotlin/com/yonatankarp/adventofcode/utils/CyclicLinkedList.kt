package com.yonatankarp.adventofcode.utils

/**
 * A cyclic linked list implementation, where the 1st and last elements in the
 * list are always connected.
 */
class CyclicLinkedList<E : Any> {
    private var listSize = 0
    private var head: Node<E>? = null
    private var tail: Node<E>? = null

    /**
     * A representation of a single element in the list.
     *
     * @param element the element that is hold by this node.
     * @param next a pointer to the next element in the list.
     */
    inner class Node<E>(var element: E, var next: Node<E>?)

    val first: E? get() = head?.element
    val last: E? get() = tail?.element
    val size: Int get() = listSize

    fun removeFirst() = unlinkHead()
    fun removeLast() = unlinkTail()
    fun addFirst(element: E) = linkHead(element)
    fun addLast(element: E) = linkTail(element)
    fun add(element: E) = linkTail(element)

    /**
     * Add all the given elements in a given index into the list.
     *
     * @param index the index to add the elements.
     * @param elementsToAdd a list of elements to add to the list
     */
    fun <T> addAll(index: Int, elementsToAdd: Collection<T>): Boolean where T : E {
        validatePositionIndex(index)

        if (elementsToAdd.isEmpty()) return false

        var (predecessor, successor) = when (index) {
            0 -> Pair(head, null)
            listSize -> Pair(null, tail)
            else -> {
                val predecessor = getNode(index - 1)
                Pair(predecessor, predecessor.next)
            }
        }

        for (element in elementsToAdd) {
            val newNode = Node(element as E, null)
            if (predecessor == null) head = newNode
            else predecessor.next = newNode
            predecessor = newNode
        }

        if (successor == null) tail = predecessor
        else predecessor!!.next = successor

        listSize += elementsToAdd.size
        return true
    }

    /**
     * Removes a given element from the list. If the element exists multiple times
     * in the list, the 1st instance would be removed.
     *
     * @param element the element to remove.
     */
    fun remove(element: E): Boolean {
        var curr = head
        while (curr != null) {
            if (curr.element == element) {
                unlink(curr)
                return true
            }
            curr = curr.next
        }
        return false
    }

    /**
     * Removes an element from the list at a given index.
     *
     * @param index the index to be removed.
     */
    fun remove(index: Int): E {
        validateElementIndex(index)
        return unlink(getNode(index))
    }

    /**
     * Clear the list, and ensure that all nodes are not linked to each other.
     */
    fun clear() {
        var current = head
        while (current != null) {
            val next = current.next
            current.next = null
            current = next
        }
        tail = null
        head = null
        listSize = 0
    }

    /**
     * Overriding the get operator to allow using the list with the syntax:
     * val element = list[i]
     */
    operator fun get(index: Int): E {
        validateElementIndex(index)
        return getNode(index).element
    }

    /**
     * Overriding the get operator to allow using the list with the syntax:
     * list[i] = element
     */
    operator fun set(index: Int, element: E): E {
        validateElementIndex(index)
        val node = getNode(index)
        val oldVal = node.element
        node.element = element
        return oldVal
    }

    /**
     * Overriding the contains operator to allow using the list with the syntax:
     * element in list
     */
    operator fun contains(element: E) = indexOf(element) != -1

    /**
     * Add an element to the list in a given index.
     *
     * @param index the index to add the element.
     * @param element the element value to be added.
     */
    fun add(index: Int, element: E) {
        validatePositionIndex(index)
        if (index == listSize) linkTail(element)
        else linkBefore(element, getNode(index))
    }

    /**
     * Find the 1st index of a given element. If the element does not exist, -1
     * is returned.
     */
    fun indexOf(element: E): Int {
        var index = 0
        var node = head
        while (node != null) {
            if (element == node.element) return index
            index++
            node = node.next
        }
        return -1
    }

    private fun linkHead(element: E) {
        val oldHead = head
        val newHead = Node(element, oldHead)
        head = newHead
        if (oldHead == null) {
            tail = newHead
        }
        listSize++
    }

    private fun linkTail(element: E) {
        val oldTail = tail
        val newTail = Node(element, null)
        tail = newTail
        if (oldTail == null) head = newTail
        else oldTail.next = newTail

        listSize++
    }

    /*
     * Links a new node before the given node.
     */
    private fun linkBefore(element: E, successor: Node<E>) {
        val previous = getPrevious(successor)
        val newNode = Node(element, successor)
        if (previous == null) head = newNode
        else previous.next = newNode
        listSize++
    }

    private fun unlinkHead() {
        head?.let {
            val next = it.next
            it.next = null
            head = next
            if (next == null) tail = null
            listSize--
        }
    }

    private fun unlinkTail() {
        tail?.let {
            val previous = getPrevious(it)
            tail = previous
            if (previous == null) head = null
            else previous.next = null
            listSize--
        }
    }

    private fun unlink(node: Node<E>): E {
        val element = node.element
        val next = node.next
        val previous = getPrevious(node)

        if (previous == null) head = next
        else {
            previous.next = next
            node.next = null
        }

        if (next == null) {
            previous?.next = null
            tail = previous
        } else {
            previous?.next = next
            node.next = null
        }

        listSize--
        return element
    }

    /*
     * Returns the previous node, or null if this node doesn't exist in the list.
     */
    private fun getPrevious(node: Node<E>): Node<E>? {
        if (head != null && node == head) return null
        var current = head
        while (current != null) {
            if (current.next == node) return current
            current = current.next
        }
        return null
    }

    private fun getNode(index: Int): Node<E> {
        var node = head
        for (i in 0 until index) {
            node = node!!.next
        }
        return node!!
    }

    private fun validateElementIndex(index: Int) {
        if (index < 0 || index >= listSize)
            throw IndexOutOfBoundsException(outOfBoundsMsg(index))
    }

    private fun validatePositionIndex(index: Int) {
        if (index in (listSize + 1)..-1)
            throw IndexOutOfBoundsException(outOfBoundsMsg(index))
    }

    private fun outOfBoundsMsg(index: Int): String {
        return "Index: $index, Size: $listSize"
    }

    override fun toString(): String {
        var current = head
        return if (current == null) "[]"
        else {
            val sb = StringBuilder()
            sb.append('[')
            while (current != null) {
                sb.append(current.element)
                current = current.next
                if (current?.element == null) {
                    sb.append(']')
                } else {
                    sb.append(',').append(' ')
                }
            }
            sb.toString()
        }
    }
}
