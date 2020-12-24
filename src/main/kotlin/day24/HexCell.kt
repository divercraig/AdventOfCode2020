package day24

import java.util.*
import kotlin.reflect.typeOf

enum class HexDirection {
    E, W, NE, SE, NW, SW
}

class HexCell() {
    var x: Int = 0
    var y: Int = 0

    constructor(_x: Int, _y: Int) : this() {
        this.x = _x
        this.y = _y
    }

    constructor(directions: String) : this() {
        var steps = processDirections(directions)
        var posX = 0
        var posY = 0
        for(direction in steps) {
            when(direction) {
                HexDirection.E -> { posX += 2 }
                HexDirection.W -> {posX -= 2}
                HexDirection.NE -> {posX++; posY++}
                HexDirection.SE -> {posX++; posY--}
                HexDirection.SW -> {posX--; posY--}
                HexDirection.NW -> {posX--; posY++}
            }
        }
        this.x = posX
        this.y = posY
    }

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is HexCell) {
            return false
        }
        if(this.x == other.x && this.y == other.y) {
            return true
        }
        return false
    }

    override fun hashCode(): Int {
        return Objects.hash(x,y)
    }

    private fun processDirections(directions: String) : List<HexDirection> {
        var currentString = ""
        var steps = mutableListOf<HexDirection>()
        for(char in directions) {
            currentString += char
            if(char == 'w' || char == 'e') {
                steps.add( when(currentString) {
                    "w" -> HexDirection.W
                    "e" -> HexDirection.E
                    "se" -> HexDirection.SE
                    "ne" -> HexDirection.NE
                    "nw" -> HexDirection.NW
                    "sw" -> HexDirection.SW
                    else -> throw Exception("Cant understand direction")
                })
                currentString = ""
            }
        }
        return steps
    }

}

