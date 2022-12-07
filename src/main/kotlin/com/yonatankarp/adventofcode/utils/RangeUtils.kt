package com.yonatankarp.adventofcode.utils

/**
 * Returns true if this range is fully contained within the other range, and
 * false otherwise.
 */
fun IntRange.containedIn(other: IntRange) =
    this.first >= other.first && this.last <= other.last

/**
 * Returns true if any of the values in this range are included in the other
 * range, and false otherwise.
 */
fun IntRange.anyContainedIn(other: IntRange) =
    this.any { other.contains(it) }
