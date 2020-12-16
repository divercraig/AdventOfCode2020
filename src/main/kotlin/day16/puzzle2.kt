@file:JvmName("Puzzle2")

package day16

fun main() {
    val translator = TicketTranslator(fileName = "src/main/resources/day16/input.txt")
    val myMappedTicket = translator.identifyColumns()

    var productOfDepartureFields = 1L
    for(entry in myMappedTicket) {
        if(entry.key.startsWith("departure")) {
            productOfDepartureFields *= entry.value.toLong()
        }
    }

    print("The product of departure fields is $productOfDepartureFields")
}