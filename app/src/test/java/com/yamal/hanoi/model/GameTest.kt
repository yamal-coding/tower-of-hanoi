package com.yamal.hanoi.model

import org.junit.Assert.assertTrue
import org.junit.Test

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

    private companion object {
        const val SOME_NUM_OF_DISKS = 3
    }
}
