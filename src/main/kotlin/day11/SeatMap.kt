package day11

import java.io.File

class SeatMap(fileName: String) {
    private var map = mutableMapOf<Pair<Int, Int>, Char>()
    private val importantSeats = mutableMapOf<Pair<Int, Int>, Set<Pair<Int,Int>>>()
    private var xSize = 0
    private var ySize = 0

    init {
        var y = -1
        File(fileName).forEachLine {
            y++

            if(it.length > xSize) {
                xSize = it.length
            }

            it.forEachIndexed { x, c -> map[Pair(x, y)] = c }
        }
        ySize = y+1
        calculateImportantSeats()
    }

    private fun isOccupied(c: Char?): Boolean {
        return c == '#'
    }

    private fun isSeat(c: Char?): Boolean {
        return c == '#' || c == 'L'
    }

    private fun numberOfAdjacentSeatsOccupied(pos: Pair<Int, Int>): Int {
        var occupiedSeats = 0

        for( xOffset in (-1..1)) {
            for(yOffset in (-1..1)) {
                if (yOffset == 0 && xOffset == 0) {
                    continue
                }
                var x = pos.first + xOffset
                var y = pos.second + yOffset
                if(x < 0 || y < 0 || x >= xSize || y >=ySize) {
                    continue
                }
                val loc = map[Pair(x,y)]
                if(isSeat(loc) && isOccupied(loc)) {
                    occupiedSeats++
                }
            }
        }

        return occupiedSeats
    }

    private fun numberOfImportantSeatsOccupied(pos: Pair<Int, Int>): Int {
        var occupiedSeats = 0

        for(seatLoc in importantSeats[pos]!!) {
            if(isOccupied(map[seatLoc])) {
                occupiedSeats++
            }
        }

        return occupiedSeats
    }

    private fun processRules(): Boolean {
        var changes = false
        var newMap = map.toMutableMap()

        for(x in 0..xSize) {
            for(y in 0..ySize) {
                val point = Pair(x, y)
                val location = map[point]
                if(isSeat(location) && !isOccupied(location) && numberOfAdjacentSeatsOccupied(point) == 0) {
                    newMap[point] = '#'
                    changes = true
                    continue
                }

                if(isSeat(location) && isOccupied(location) && numberOfAdjacentSeatsOccupied(point) > 3) {
                    newMap[point] = 'L'
                    changes = true
                }
            }
        }
        this.map = newMap
        return changes
    }

    private fun processRealRules(): Boolean {
        var changes = false
        var newMap = map.toMutableMap()

        for(x in 0..xSize) {
            for(y in 0..ySize) {
                val point = Pair(x, y)
                val location = map[point]
                if(isSeat(location) && !isOccupied(location) && numberOfImportantSeatsOccupied(point) == 0) {
                    newMap[point] = '#'
                    changes = true
                    continue
                }

                if(isSeat(location) && isOccupied(location) && numberOfImportantSeatsOccupied(point) > 4) {
                    newMap[point] = 'L'
                    changes = true
                }
            }
        }
        this.map = newMap
        return changes
    }

    private fun occupiedSeats(): Int {
        var occupied = 0
        for(x in 0..xSize) {
            for(y in 0..ySize) {
                val point = Pair(x, y)
                val location = map[point]
                if(isOccupied(location)) {
                    occupied++
                }
            }
        }
        return occupied
    }

    fun seatsOccupiedAfterChaos(): Int {
        var changes = processRules()
        while (changes) {
            changes = processRules()
        }

        return occupiedSeats()
    }

    private fun calculateImportantSeats() {
        for(x in 0..xSize) {
            for(y in 0..ySize) {
                val loc = Pair(x, y)
                importantSeats[loc] = calculateImportantSeats(loc)
            }
        }
    }

    private fun calculateImportantSeats(loc: Pair<Int, Int>): Set<Pair<Int, Int>> {
        val importantSeats = mutableSetOf<Pair<Int, Int>>()
        for(xOffset in -1..1) {
            for(yOffset in -1..1) {
                if(xOffset == 0 && yOffset == 0) {
                    continue
                }
                var foundSeat = false
                var x = loc.first
                var y = loc.second
                while(!foundSeat) {
                    x += xOffset
                    y += yOffset
                    if(x < 0 || y < 0 || x >= xSize || y >= ySize) {
                        foundSeat = true
                    }
                    else if(isSeat(map[Pair(x,y)])) {
                        foundSeat = true
                        importantSeats.add(Pair(x, y))
                    }
                }

            }
        }
        return importantSeats
    }

    fun seatsOccupiedWithRealPeople(): Int {
        var changes = processRules()
        while (changes) {
            changes = processRealRules()
        }

        return occupiedSeats()
    }


}