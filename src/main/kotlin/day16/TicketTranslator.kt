package day16

import java.io.BufferedReader
import java.io.File
import java.io.IOException

class TicketTranslator(fileName: String) {
    val rules = mutableMapOf<String, List<IntRange>>()
    var myTicket = mutableListOf<Int>()
    var nearbyTickets = mutableListOf<List<Int>>()
    var validTickets = mutableListOf<List<Int>>()

    init {
        val reader = File(fileName).inputStream().bufferedReader()
        readRules(reader)
        readMyTicket(reader)
        readOtherTickets(reader)
        rejectInvalidTickets()
    }

    private fun readRules(reader: BufferedReader) {
        val nameRegex = """^(.+):""".toRegex()
        var stillReadingRules = true
        while(stillReadingRules) {
            val line = reader.readLine()
            val result = nameRegex.find(line)
            if(result != null) {
                val name = result.groups[1]!!.value
                if(name == "your ticket") {
                    stillReadingRules = false
                } else {
                    rules[name] = parseRule(line)
                }
            }
        }
    }

    private fun parseRule(line: String): List<IntRange> {
        val ranges = mutableListOf<IntRange>()
        val ruleRegex = """^.+: (\d+-\d+) or (\d+-\d+)$""".toRegex()
        val result = ruleRegex.find(line)
        for(i in 1..2) {
            val digits = result!!.groupValues[i].split('-')
            ranges.add(IntRange(digits[0].toInt(), digits[1].toInt()))
        }
        return ranges
    }

    private fun readMyTicket(reader:BufferedReader) {
        val line = reader.readLine()
        val ticketValues = mutableListOf<Int>()
        for(numString in line.split(',')) {
            ticketValues.add(numString.toInt())
        }
        myTicket = ticketValues
    }

    private fun readOtherTickets(reader:BufferedReader) {
        var foundNearbyTicket = false

        while(!foundNearbyTicket) {
            val line = reader.readLine()
            if(line == "nearby tickets:") {
                foundNearbyTicket = true
            }
        }
        var continueReadingLines = true
        while(continueReadingLines) {
            try {
                val line = reader.readLine()
                if(line != null) {
                    val ticketValues = mutableListOf<Int>()
                    for (numString in line.split(',')) {
                        ticketValues.add(numString.toInt())
                    }
                    nearbyTickets.add(ticketValues)
                } else {
                    continueReadingLines = false
                }
            } catch(e: IOException) {
                continueReadingLines = false
            }
        }
        reader.close()
    }

    private fun rejectInvalidTickets() {
        for(ticket in nearbyTickets) {
            var anyInvalidFields = false
            for(field in ticket) {
                if(!fieldCanBeValid(field)) {
                    anyInvalidFields = true
                }
            }
            if(!anyInvalidFields) {
                validTickets.add(ticket)
            }
        }
    }

    private fun fieldCanBeValid(field: Int) : Boolean {
        for(rule in rules.values) {
            for(check in rule) {
                if(check.contains(field)) {
                    return true
                }
            }
        }
        return false
    }

    private fun fieldIsValidForRule(field: Int, rule: List<IntRange>): Boolean {
        for(check in rule) {
            if(check.contains(field)) {
                return true
            }
        }
        return false
    }

    fun sumOfInvalidFields() : Int {
        var sumOfInvalidFields = 0
        for(ticket in nearbyTickets) {
            for(field in ticket) {
                if(!fieldCanBeValid(field)) {
                    sumOfInvalidFields += field
                }
            }
        }
        return sumOfInvalidFields
    }

    fun identifyColumns(): Map<String, Int> {
        var columnPossibleRules = mutableListOf<MutableSet<String>>()

        for (i in 0 until myTicket.size) {
            columnPossibleRules.add(i, rules.keys.toMutableSet())
            for(ticket in validTickets) {
                val field = ticket[i]
                for(rule in rules) {
                    if(!fieldIsValidForRule(field, rule.value)) {
                        columnPossibleRules[i].remove(rule.key)
                    }
                }
            }
        }
        var rulesReduced = true
        while(rulesReduced){
            rulesReduced = false
            var rulesToRemove = mutableSetOf<String>()

            for(possibleRules in columnPossibleRules) {
                if(possibleRules.size == 1) {
                    rulesToRemove.add(possibleRules.first())
                }
            }

            for(ruleToRemove in rulesToRemove) {
                for(possibleRules in columnPossibleRules) {
                    if(possibleRules.size > 1 && possibleRules.contains(ruleToRemove)) {
                        possibleRules.remove(ruleToRemove)
                        rulesReduced = true
                    }
                }
            }
        }

        val myMappedTicket = mutableMapOf<String, Int>()
        myTicket.forEachIndexed { index, fieldValue ->
            myMappedTicket.put(columnPossibleRules[index].first(), fieldValue)
        }

        return myMappedTicket
    }

}