package com.yonatankarp.adventofcode.utils

fun IntRange.containedIn(other: IntRange) =
    this.first >= other.first && this.last <= other.last

fun IntRange.anyContainedIn(other: IntRange) =
    this.any { other.contains(it) }
