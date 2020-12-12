package day12

import java.io.File
import kotlin.math.absoluteValue

class Navigation(fileName: String) {
    val instructions = mutableListOf<Pair<Char, Int>>()
    var xPos = 0
    var yPos = 0
    var heading = 90

    var waypointX = 10
    var waypointY = 1

    val NORTH = 'N'
    val SOUTH = 'S'
    val EAST = 'E'
    val WEST = 'W'
    val LEFT = 'L'
    val RIGHT = 'R'
    val FORWARD = 'F'

    init {
        File(fileName).forEachLine {
            val mavouver = it[0]
            val number = it.substring(1).toInt()
            instructions.add(Pair(mavouver, number))
        }
    }

    fun areAllTurnsMultipleOf90(): Boolean {
        var correct = true
        for(instruction in instructions) {
            if(instruction.first in listOf('R', 'L')) {
                if(instruction.second % 90 != 0) {
                    correct = false
                    println("Not a 90 degree turn: ${instruction.first}${instruction.second}")
                }
            }
        }
        return correct
    }

    private fun turn(direction: Char, degrees: Int) {
        when(direction) {
            LEFT -> heading -= degrees
            RIGHT -> heading += degrees
        }

        while(heading >= 360) {
            heading -= 360
        }

        while(heading < 0) {
            heading += 360
        }
    }

    private fun moveForward(distance: Int) {
        when(this.heading) {
            0 -> this.yPos += distance
            90 -> this.xPos += distance
            180 -> this.yPos -= distance
            270 -> this.xPos -= distance
        }
    }

    fun distanceAfterNav(): Int {
        for(instruction in instructions) {
            when(instruction.first) {
                NORTH -> this.yPos += instruction.second
                SOUTH -> this.yPos -= instruction.second
                EAST -> this.xPos += instruction.second
                WEST -> this.xPos -= instruction.second
                LEFT,RIGHT -> this.turn(instruction.first, instruction.second)
                FORWARD -> this.moveForward(instruction.second)
            }
        }

        return xPos.absoluteValue + yPos.absoluteValue
    }

    private fun rotateWaypoint(direction: Char, degrees: Int) {
        var remainingTurn = degrees
        while(remainingTurn > 0) {
            remainingTurn -= 90
            when(direction) {
                LEFT -> {
                    var newX = waypointY * -1
                    var newY = waypointX
                    waypointX = newX
                    waypointY = newY
                }
                RIGHT -> {
                    var newX = waypointY
                    var newY = waypointX * -1
                    waypointX = newX
                    waypointY = newY
                }
            }
        }
    }

    private fun moveToWaypoint(times: Int) {
        var xOffset = waypointX * times
        var yOffset = waypointY * times
        xPos += xOffset
        yPos += yOffset
    }

    fun distanceAfterWaypointing(): Int {
        for(instruction in instructions) {
            when(instruction.first) {
                NORTH-> this.waypointY += instruction.second
                SOUTH-> this.waypointY -= instruction.second
                EAST-> this.waypointX += instruction.second
                WEST-> this.waypointX -= instruction.second
                LEFT,RIGHT -> this.rotateWaypoint(instruction.first, instruction.second)
                FORWARD-> moveToWaypoint(instruction.second)
            }
        }
        return xPos.absoluteValue + yPos.absoluteValue
    }
}