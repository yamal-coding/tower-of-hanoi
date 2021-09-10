package com.yamal.hanoi.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.pow

class GameTest {

    @Test
    fun `Game is initialized with all disks on first stack`() {
        val game = Game(numOfDisks = SOME_NUM_OF_DISKS)

        assertTrue(game.firstStack().isNotEmpty())
        assertTrue(game.middleStack().isEmpty())
        assertTrue(game.lastStack().isEmpty())
    }

    @Test
    fun `Game is initialized and every disk on first stack is smaller than the disk below it`() {
        val game = Game(numOfDisks = SOME_NUM_OF_DISKS)

        val firstStack = game.firstStack()
        for (i in 0 until firstStack.size - 1) {
            assertTrue(firstStack[i] > firstStack[i + 1])
        }
    }

    @Test
    fun `Game has ended when every disk is on last stack`() {
        val game = Game(numOfDisks = SOME_NUM_OF_DISKS)

        game.moveDisk(Stack.FIRST, Stack.LAST)
        game.moveDisk(Stack.FIRST, Stack.MIDDLE)
        game.moveDisk(Stack.LAST, Stack.MIDDLE)
        game.moveDisk(Stack.FIRST, Stack.LAST)
        game.moveDisk(Stack.MIDDLE, Stack.FIRST)
        game.moveDisk(Stack.MIDDLE, Stack.LAST)
        game.moveDisk(Stack.FIRST, Stack.LAST)

        assertTrue(game.hasEnded())
    }

    @Test
    fun `Number of moves increments on each move`() {
        val game = Game(numOfDisks = SOME_NUM_OF_DISKS)

        assertEquals(0, game.numOfMoves)

        game.moveDisk(Stack.FIRST, Stack.MIDDLE)

        assertEquals(1, game.numOfMoves)
    }

    @Test
    fun `Optimus number of moves is calculated when game is initialized`() {
        val game = Game(numOfDisks = SOME_NUM_OF_DISKS)

        assertEquals(2.0.pow(SOME_NUM_OF_DISKS).toInt() - 1, game.optimusNumOfMoves)
    }

    @Test
    fun `Move a disk to an empty stack is allowed`() {
        val game = Game(numOfDisks = SOME_NUM_OF_DISKS)

        assertTrue(game.canMoveDisk(Stack.FIRST, Stack.MIDDLE))
    }

    @Test
    fun `Put a disk on top of a bigger disk is allowed`() {
        val game = Game(numOfDisks = SOME_NUM_OF_DISKS)
        game.moveDisk(Stack.FIRST, Stack.MIDDLE)

        assertTrue(game.canMoveDisk(Stack.MIDDLE, Stack.FIRST))
    }

    @Test
    fun `Put a disk on top of an smaller disk is not allowed`() {
        val game = Game(numOfDisks = SOME_NUM_OF_DISKS)
        game.moveDisk(Stack.FIRST, Stack.MIDDLE)

        assertFalse(game.canMoveDisk(Stack.FIRST, Stack.MIDDLE))
    }

    @Test
    fun `Move a disk into the same stack is not allowed`() {
        val game = Game(numOfDisks = SOME_NUM_OF_DISKS)

        assertFalse(game.canMoveDisk(Stack.FIRST, Stack.FIRST))
    }

    @Test
    fun `Move a disk from an empty stack is not allowed`() {
        val game = Game(numOfDisks = SOME_NUM_OF_DISKS)

        assertFalse(game.canMoveDisk(Stack.MIDDLE, Stack.FIRST))
    }

    private companion object {
        const val SOME_NUM_OF_DISKS = 3
    }
}
