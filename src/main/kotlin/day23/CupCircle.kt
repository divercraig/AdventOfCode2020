package day23

class CupCircle(val input: List<Int>) {
    var currentCircle: MutableList<Int>
    val max: Int
    val min: Int
    var currentCupIndex = 0

    init {
        currentCircle = input.toMutableList()
        max = input.max()!!
        min = input.min()!!
    }

    fun playOneRound() {
        val currentCupLabel = currentCircle[currentCupIndex]
        val removedCups = mutableListOf<Int>()
        repeat(3) {
            if(currentCupIndex +1 >= currentCircle.size) {
                removedCups.add(currentCircle.removeAt(0))
                currentCupIndex = currentCircle.indexOf(currentCupLabel)
            } else {
                removedCups.add(currentCircle.removeAt(currentCupIndex+1))
            }
        }
        var destinationCup = currentCupLabel - 1
        while(!currentCircle.contains(destinationCup)) {
            destinationCup--
            if(destinationCup < min) {
                destinationCup = max
            }
        }
        val destinationCupIndex = currentCircle.indexOf(destinationCup)
        for(removedCup in removedCups.reversed()) {
            currentCircle.add(destinationCupIndex+1, removedCup)
        }
        currentCupIndex = currentCircle.indexOf(currentCupLabel)
        currentCupIndex++
        if(currentCupIndex>=currentCircle.size) {
            currentCupIndex=0
        }
    }

    fun playRounds(num: Int) {
        repeat(num) {
            playOneRound()
        }
    }

    fun finalState(): String {
        var result = ""
        var index = currentCircle.indexOf(1)
        index++
        while(index< currentCircle.size) {
            result = result + currentCircle.removeAt(index).toString()
        }
        index = 0
        while(currentCircle[index] != 1) {
            result = result + currentCircle.removeAt(index).toString()
        }
        return result
    }
}