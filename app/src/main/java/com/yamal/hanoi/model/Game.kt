package com.yamal.hanoi.model

import kotlin.math.pow

class Game(
    numOfDisks: Int
) {
    private val stacks = mapOf(
        Stack.FIRST to java.util.Stack<Int>(),
        Stack.MIDDLE to java.util.Stack<Int>(),
        Stack.LAST to java.util.Stack<Int>()
    )

    var numOfMoves: Int = 0
        private set

    val optimusNumOfMoves = 2.0.pow(numOfDisks).toInt() - 1

    init {
        if (numOfDisks < 1) {
            throw IllegalArgumentException("Invalid numOfDisks=$numOfDisks. It must be a positive number.")
        }

        for (disk in numOfDisks downTo 1) {
            stacks[Stack.FIRST]?.push(disk)
        }
    }

    fun firstStack(): List<Int> = stacks(Stack.FIRST)

    fun middleStack(): List<Int> = stacks(Stack.MIDDLE)

    fun lastStack(): List<Int> = stacks(Stack.LAST)

    fun canMoveDisk(from: Stack, to: Stack): Boolean =
        when {
            stacks(from).isEmpty() ||
            from == to ||
            !stacks(to).isEmpty() && stacks(from).peek() > stacks(to).peek() -> false
            else -> true
        }

    fun moveDisk(from: Stack, to: Stack) {
        when {
            stacks(from).isEmpty() -> throw IllegalArgumentException("No disk to move from rod $from")
            from == to -> throw IllegalArgumentException("Moving a disk to the same rod it currently is is placed not a valid move.")
            !stacks(to).isEmpty() && stacks(from).peek() > stacks(to).peek() -> throw IllegalArgumentException("A disk greater than the target")
        }

        val diskToMove = stacks(from).pop()
        stacks(to).push(diskToMove)
        numOfMoves++
    }

    fun hasEnded(): Boolean =
        stacks(Stack.FIRST).isEmpty() && stacks(Stack.MIDDLE).isEmpty()

    private fun stacks(stack: Stack): java.util.Stack<Int> = stacks[stack]!!
}
