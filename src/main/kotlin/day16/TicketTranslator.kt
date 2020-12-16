package day16

import java.io.BufferedReader
import java.io.File
import java.io.IOException

class TicketTranslator(fileName: String) {
    val rules = mutableMapOf<String, List<IntRange>>()
    var myTicket = mutableListOf<Int>()
    var nearbyTickets = mutableListOf<List<Int>>()

    init {
        val reader = File(fileName).inputStream().bufferedReader()
        readRules(reader)
        readMyTicket(reader)
        readOtherTickets(reader)
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

    fun fieldCanBeValid(field: Int) : Boolean {
        for(rule in rules.values) {
            for(check in rule) {
                if(check.contains(field)) {
                    return true
                }
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

}