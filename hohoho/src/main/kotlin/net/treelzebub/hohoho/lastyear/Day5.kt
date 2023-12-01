package net.treelzebub.hohoho.lastyear

import net.treelzebub.hohoho.splitDoubleNewLine
import net.treelzebub.hohoho.splitNewLine
import java.util.ArrayDeque

object Day5 {

    data class Instruction(
        val numCrates: Int,
        val from: Int,
        val to: Int
    )

    fun go() {
//        part1()
        part2()
    }

    private fun part1() {
        val moved = moveCrates(false)
        val tops = moved.map { it.first() }
        println("Part 1: Tops of Stacks are: ${tops.joinToString("")}")
    }

    private fun moveCrates(preserveOrder: Boolean): List<ArrayDeque<Char>> {
        val (rawMatrix, rawInstructions) = input.splitDoubleNewLine()
            .let { it[0].splitNewLine().dropLast(1) to it[1].splitNewLine() }
        val columnList = buildListOfColumns(rawMatrix)
        val instructions = parseInstructions(rawInstructions)
        return moveCrates(columnList, instructions, preserveOrder)
    }

    private fun getItem(row: String, num: Int): Char = row[4 * (num - 1) + 1]

    // List of Columns, listed from top to bottom
    private fun buildListOfColumns(stacks: List<String>): List<ArrayDeque<Char>> {
        val list: MutableList<ArrayDeque<Char>> = mutableListOf()
        val numStacks = (stacks.first().length / 4) + 1
        for (col in 1..numStacks) {
            val temp = ArrayDeque<Char>()
            for (row in stacks.lastIndex downTo 0) {
                val item = getItem(stacks[row], col)
                if (item == ' ') continue else temp.push(item)
            }
            list.add(temp)
        }
        return list
    }

    private fun parseInstructions(instructions: List<String>): List<Instruction> {
        val digits = instructions.map { line ->
            line.split(" ").filter { item ->
                item.contains(Regex("\\d"))
            }

        }
        return digits.map { Instruction(it[0].toInt(), it[1].toInt(), it[2].toInt()) }
    }

    private fun moveCrates(columnList: List<ArrayDeque<Char>>, instructions: List<Instruction>, preserveOrder: Boolean): List<ArrayDeque<Char>> {
        val mutable = columnList.toMutableList()
        instructions.forEach {
            val from = mutable[it.from - 1]
            val to = mutable[it.to - 1]
            if (preserveOrder) {
                val crates = from.take(it.numCrates).reversed()
                crates.forEach { to.push(it) }
                mutable[it.from - 1] = ArrayDeque(from.drop(it.numCrates))
            } else {
                repeat(it.numCrates) {
                    to.push(from.pop())
                }
            }
        }
        return mutable
    }

    private fun part2() {
        val moved = moveCrates(true)
        val tops = moved.map { it.first() }
        println("Part 1: Tops of Stacks are: ${tops.joinToString("")}")
    }
}

private val test = """    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2"""

private val input = """                [B]     [L]     [S]
        [Q] [J] [C]     [W]     [F]
    [F] [T] [B] [D]     [P]     [P]
    [S] [J] [Z] [T]     [B] [C] [H]
    [L] [H] [H] [Z] [G] [Z] [G] [R]
[R] [H] [D] [R] [F] [C] [V] [Q] [T]
[C] [J] [M] [G] [P] [H] [N] [J] [D]
[H] [B] [R] [S] [R] [T] [S] [R] [L]
 1   2   3   4   5   6   7   8   9 

move 8 from 7 to 1
move 9 from 1 to 9
move 4 from 5 to 4
move 4 from 6 to 1
move 3 from 8 to 5
move 6 from 5 to 9
move 1 from 5 to 1
move 4 from 4 to 9
move 7 from 3 to 7
move 6 from 7 to 3
move 1 from 8 to 7
move 2 from 7 to 6
move 1 from 8 to 9
move 1 from 6 to 3
move 4 from 3 to 5
move 5 from 1 to 3
move 1 from 1 to 8
move 2 from 3 to 4
move 1 from 4 to 1
move 7 from 9 to 2
move 1 from 6 to 3
move 2 from 1 to 9
move 20 from 9 to 7
move 6 from 4 to 9
move 1 from 2 to 9
move 6 from 9 to 4
move 1 from 4 to 6
move 1 from 8 to 6
move 1 from 4 to 7
move 5 from 2 to 3
move 2 from 6 to 4
move 3 from 9 to 5
move 5 from 3 to 5
move 3 from 3 to 8
move 3 from 5 to 6
move 1 from 9 to 8
move 5 from 4 to 5
move 3 from 4 to 9
move 1 from 8 to 2
move 2 from 8 to 2
move 11 from 5 to 6
move 16 from 7 to 1
move 2 from 1 to 7
move 14 from 6 to 1
move 11 from 1 to 6
move 2 from 1 to 4
move 4 from 3 to 4
move 9 from 2 to 4
move 2 from 4 to 8
move 2 from 5 to 3
move 9 from 4 to 7
move 2 from 3 to 2
move 1 from 2 to 7
move 1 from 8 to 4
move 4 from 1 to 4
move 1 from 9 to 1
move 7 from 4 to 7
move 2 from 6 to 5
move 1 from 8 to 6
move 1 from 4 to 2
move 10 from 1 to 6
move 5 from 7 to 3
move 1 from 4 to 7
move 2 from 1 to 2
move 2 from 2 to 4
move 4 from 3 to 4
move 18 from 7 to 6
move 6 from 6 to 4
move 1 from 7 to 4
move 1 from 7 to 6
move 11 from 4 to 5
move 14 from 5 to 9
move 1 from 8 to 7
move 8 from 6 to 2
move 2 from 4 to 5
move 7 from 9 to 1
move 6 from 9 to 7
move 5 from 1 to 8
move 1 from 3 to 6
move 10 from 6 to 3
move 1 from 9 to 6
move 1 from 5 to 4
move 4 from 3 to 8
move 1 from 5 to 9
move 9 from 2 to 3
move 1 from 9 to 5
move 4 from 8 to 4
move 1 from 5 to 3
move 5 from 8 to 7
move 5 from 7 to 2
move 3 from 4 to 1
move 8 from 6 to 5
move 1 from 7 to 9
move 4 from 1 to 3
move 2 from 4 to 6
move 5 from 5 to 2
move 4 from 6 to 9
move 1 from 1 to 2
move 1 from 5 to 6
move 7 from 2 to 8
move 5 from 6 to 8
move 4 from 7 to 9
move 15 from 3 to 9
move 1 from 7 to 3
move 1 from 5 to 3
move 6 from 2 to 6
move 1 from 5 to 2
move 2 from 3 to 9
move 1 from 6 to 8
move 5 from 8 to 9
move 2 from 3 to 8
move 3 from 3 to 6
move 11 from 9 to 4
move 1 from 2 to 1
move 2 from 8 to 4
move 1 from 1 to 4
move 7 from 4 to 7
move 9 from 6 to 3
move 4 from 7 to 8
move 4 from 7 to 6
move 19 from 9 to 4
move 7 from 8 to 5
move 5 from 3 to 6
move 6 from 6 to 9
move 3 from 3 to 5
move 1 from 3 to 9
move 8 from 4 to 5
move 2 from 9 to 6
move 3 from 8 to 2
move 1 from 8 to 4
move 1 from 2 to 5
move 19 from 4 to 1
move 2 from 5 to 7
move 2 from 2 to 4
move 13 from 5 to 2
move 1 from 5 to 1
move 2 from 6 to 9
move 1 from 8 to 7
move 9 from 9 to 3
move 2 from 3 to 8
move 1 from 4 to 2
move 5 from 6 to 7
move 1 from 4 to 6
move 2 from 8 to 7
move 7 from 1 to 5
move 1 from 6 to 7
move 10 from 1 to 8
move 1 from 1 to 3
move 1 from 1 to 2
move 6 from 5 to 3
move 4 from 5 to 3
move 5 from 7 to 1
move 3 from 1 to 2
move 4 from 7 to 5
move 8 from 3 to 6
move 2 from 1 to 7
move 4 from 5 to 8
move 7 from 3 to 5
move 3 from 7 to 2
move 1 from 7 to 3
move 12 from 2 to 8
move 23 from 8 to 2
move 16 from 2 to 6
move 1 from 9 to 6
move 7 from 5 to 7
move 7 from 2 to 4
move 2 from 3 to 8
move 1 from 1 to 9
move 5 from 8 to 1
move 2 from 3 to 9
move 2 from 7 to 1
move 4 from 1 to 3
move 4 from 7 to 2
move 2 from 1 to 4
move 11 from 2 to 9
move 3 from 3 to 4
move 1 from 9 to 1
move 2 from 2 to 7
move 4 from 4 to 8
move 2 from 9 to 5
move 2 from 5 to 7
move 4 from 4 to 6
move 1 from 3 to 8
move 1 from 9 to 8
move 4 from 4 to 2
move 2 from 1 to 3
move 1 from 8 to 4
move 2 from 3 to 5
move 3 from 9 to 7
move 2 from 8 to 9
move 1 from 9 to 6
move 2 from 7 to 3
move 2 from 8 to 1
move 1 from 4 to 9
move 18 from 6 to 2
move 1 from 6 to 5
move 1 from 5 to 9
move 18 from 2 to 3
move 1 from 8 to 7
move 2 from 5 to 9
move 1 from 1 to 4
move 3 from 2 to 1
move 9 from 9 to 4
move 7 from 4 to 6
move 2 from 7 to 3
move 2 from 4 to 9
move 7 from 6 to 7
move 3 from 7 to 2
move 7 from 6 to 3
move 2 from 6 to 9
move 24 from 3 to 9
move 2 from 6 to 8
move 1 from 4 to 2
move 2 from 8 to 5
move 31 from 9 to 3
move 6 from 7 to 4
move 35 from 3 to 7
move 1 from 1 to 8
move 1 from 5 to 7
move 1 from 5 to 4
move 1 from 3 to 9
move 1 from 8 to 2
move 3 from 1 to 7
move 7 from 4 to 5
move 1 from 9 to 8
move 4 from 5 to 6
move 2 from 5 to 2
move 6 from 2 to 5
move 2 from 5 to 7
move 2 from 2 to 1
move 2 from 5 to 4
move 1 from 8 to 4
move 3 from 4 to 6
move 4 from 6 to 7
move 1 from 5 to 2
move 2 from 6 to 9
move 1 from 6 to 4
move 1 from 4 to 8
move 2 from 9 to 6
move 1 from 8 to 9
move 34 from 7 to 9
move 6 from 7 to 3
move 1 from 7 to 2
move 1 from 5 to 8
move 1 from 8 to 6
move 6 from 7 to 4
move 1 from 7 to 3
move 7 from 3 to 5
move 6 from 4 to 6
move 31 from 9 to 1
move 3 from 5 to 7
move 24 from 1 to 3
move 1 from 2 to 4
move 3 from 9 to 1
move 14 from 3 to 5
move 1 from 4 to 3
move 1 from 9 to 7
move 8 from 3 to 7
move 1 from 2 to 9
move 7 from 1 to 5
move 3 from 6 to 8
move 3 from 6 to 1
move 1 from 1 to 3
move 4 from 3 to 2
move 4 from 2 to 3
move 2 from 5 to 1
move 9 from 7 to 4
move 1 from 6 to 5
move 1 from 1 to 7
move 3 from 8 to 9
move 5 from 4 to 2
move 3 from 2 to 3
move 1 from 2 to 3
move 2 from 4 to 1
move 2 from 9 to 4
move 1 from 9 to 3
move 1 from 6 to 1
move 1 from 9 to 6
move 25 from 5 to 4
move 4 from 1 to 9
move 2 from 3 to 7
move 2 from 6 to 9
move 2 from 9 to 5
move 6 from 7 to 1
move 5 from 3 to 6
move 10 from 4 to 3
move 10 from 4 to 8
move 2 from 4 to 2
move 5 from 1 to 9
move 2 from 6 to 4
move 6 from 9 to 6
move 7 from 6 to 4
move 3 from 9 to 4
move 3 from 2 to 4
move 4 from 3 to 8
move 2 from 5 to 3
move 10 from 4 to 9
move 4 from 9 to 7
move 5 from 9 to 5
move 4 from 5 to 1
move 9 from 4 to 6
move 10 from 1 to 3
move 1 from 5 to 4
move 3 from 4 to 5
move 2 from 5 to 7
move 1 from 7 to 3
move 1 from 6 to 9
move 11 from 8 to 6
move 14 from 6 to 5
move 1 from 4 to 7
move 7 from 5 to 3
move 3 from 5 to 4
move 2 from 9 to 5
move 2 from 4 to 3
move 2 from 7 to 4
move 11 from 3 to 9
move 2 from 8 to 2
move 2 from 2 to 3
move 1 from 8 to 2
move 1 from 2 to 9
move 3 from 4 to 5
move 2 from 6 to 9
move 1 from 1 to 8
move 10 from 9 to 7
move 2 from 9 to 3
move 23 from 3 to 9
move 4 from 6 to 4
move 9 from 5 to 6
move 1 from 5 to 3
move 5 from 6 to 7
move 1 from 1 to 7
move 1 from 3 to 9
move 4 from 6 to 7
move 1 from 8 to 7
move 1 from 7 to 5
move 1 from 5 to 1
move 12 from 7 to 6
move 9 from 9 to 3
move 6 from 6 to 4
move 8 from 7 to 3
move 3 from 7 to 4
move 6 from 3 to 1
move 10 from 4 to 8
move 10 from 8 to 7
move 2 from 3 to 7
move 9 from 3 to 8
move 2 from 6 to 3
move 10 from 7 to 1
move 3 from 4 to 6
move 5 from 8 to 5
move 3 from 5 to 7
move 1 from 3 to 2
move 1 from 2 to 6
move 6 from 9 to 1
move 12 from 1 to 3
move 3 from 6 to 9
move 3 from 1 to 7
move 1 from 3 to 2
move 7 from 1 to 7
move 1 from 2 to 7
move 2 from 6 to 4
move 1 from 4 to 5
move 3 from 8 to 7
move 2 from 6 to 3
move 2 from 6 to 1
move 1 from 3 to 8
move 5 from 3 to 4
move 2 from 8 to 5
move 14 from 7 to 4
move 1 from 3 to 2
move 1 from 3 to 7
move 7 from 7 to 4
move 2 from 5 to 3
move 2 from 1 to 4
move 9 from 4 to 6
move 1 from 1 to 2
move 4 from 9 to 4
move 8 from 9 to 3
move 2 from 2 to 7
move 13 from 4 to 8
move 4 from 4 to 1
move 2 from 7 to 6
move 12 from 3 to 2
move 11 from 2 to 9
move 6 from 4 to 9
move 18 from 9 to 4
move 2 from 1 to 6
move 6 from 8 to 1
move 13 from 6 to 5
move 8 from 4 to 5
move 1 from 2 to 9
move 8 from 1 to 4
move 7 from 4 to 8
move 4 from 3 to 5
move 10 from 8 to 5
move 13 from 5 to 8
move 12 from 4 to 5
move 2 from 9 to 8
move 29 from 5 to 9
move 24 from 9 to 2
move 23 from 2 to 4
move 5 from 9 to 2
move 7 from 5 to 7
move 1 from 5 to 1
move 7 from 4 to 8
move 14 from 8 to 1
move 5 from 2 to 6
move 16 from 4 to 7
move 8 from 1 to 6
move 1 from 2 to 8
move 20 from 7 to 6
move 11 from 6 to 4
move 3 from 1 to 5
move 3 from 4 to 3
move 8 from 4 to 9
move 8 from 6 to 1
move 2 from 1 to 4
move 3 from 5 to 2
move 12 from 8 to 2
move 1 from 7 to 1
move 1 from 3 to 5
move 1 from 7 to 8
move 1 from 7 to 3
move 12 from 2 to 8
move 13 from 6 to 4
move 2 from 1 to 9
move 3 from 2 to 6
move 3 from 9 to 7
move 5 from 9 to 1
move 4 from 6 to 4
move 2 from 3 to 6
move 1 from 5 to 9
move 1 from 6 to 7
move 9 from 1 to 5
move 11 from 8 to 3
move 1 from 6 to 8
move 3 from 7 to 1
move 1 from 8 to 7
move 2 from 8 to 9
move 7 from 1 to 2
move 17 from 4 to 7
move 1 from 8 to 6
move 4 from 7 to 2
move 4 from 9 to 7
move 4 from 2 to 3
move 1 from 1 to 4
move 2 from 4 to 3
move 9 from 5 to 4
move 1 from 6 to 8
move 6 from 2 to 1
move 5 from 1 to 9
move 9 from 4 to 3
move 1 from 4 to 6
move 2 from 9 to 7
move 1 from 1 to 5
move 1 from 2 to 7
move 1 from 8 to 9
move 1 from 6 to 8
move 1 from 5 to 4
move 1 from 8 to 7
move 23 from 3 to 7
move 36 from 7 to 6
move 33 from 6 to 1
move 1 from 4 to 8
move 7 from 1 to 5
move 1 from 8 to 1
move 3 from 7 to 2
move 24 from 1 to 3
move 7 from 7 to 3
move 3 from 5 to 1
move 4 from 5 to 3
move 1 from 9 to 8
move 2 from 9 to 6
move 1 from 8 to 5
move 3 from 2 to 5
move 30 from 3 to 5
move 1 from 6 to 7
move 6 from 1 to 8
move 7 from 3 to 2
move 1 from 7 to 5
move 2 from 3 to 2
move 2 from 6 to 8
move 1 from 6 to 1
move 7 from 5 to 8
move 8 from 8 to 7
move 20 from 5 to 8
move 2 from 9 to 7
move 8 from 2 to 1
move 7 from 7 to 3
move 1 from 2 to 1
move 3 from 7 to 9
move 4 from 8 to 3
move 5 from 5 to 6
move 1 from 5 to 9
move 4 from 9 to 4
move 1 from 5 to 9
move 2 from 3 to 6
move 1 from 5 to 8
move 7 from 6 to 3
move 1 from 4 to 1
move 7 from 3 to 2
move 3 from 3 to 5
move 2 from 4 to 7"""