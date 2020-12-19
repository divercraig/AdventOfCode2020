package day19

import java.io.File

class MessageScanner(fileName: String) {
    val unprocessedRules: Map<Int, String>
    val processedRules = mutableMapOf<Int, String>()
    var possibleMatches = setOf<String>()
    val messages: List<String>

    init {
        var rules = true
        val reader = File(fileName).inputStream().bufferedReader()
        val mutableRules = mutableMapOf<Int, String>()
        val mutableMessages = mutableListOf<String>()

        while(rules) {
            val ruleString = reader.readLine()
            if(ruleString == "") {
                rules = false
            } else {
                val parts = ruleString.split(':')
                var checkedRule = """\d+""".toRegex().replace(parts[1]) {
                    m -> "(${m.value})"
                }
                checkedRule = checkedRule.replace("\"","")
                mutableRules[parts[0].toInt()] = checkedRule
            }
        }

        while(reader.ready()) {
            val ruleString = reader.readLine()
            mutableMessages.add(ruleString)
        }

        unprocessedRules = mutableRules
        messages = mutableMessages
    }

    fun processRules() {
        for(rule in unprocessedRules) {
            if(!rule.value.contains("""\d""".toRegex())) {
                processedRules[rule.key] = rule.value
            }
        }
        println("Completed simple rules")

        while(processedRules.size < unprocessedRules.size) {
            for (key in unprocessedRules.keys) {
                if (!processedRules.containsKey(key)) {
                    replaceKeysIfPossible(key)
                }
            }
        }

        println("Completed nested rules")
    }

    private fun replaceKeysIfPossible(key: Int){
        var rule = unprocessedRules[key]!!

        if(rule.contains('|')) {
            rule = "[$rule]"
        }

        val keys = mutableSetOf<Int>()
        val keyRegex = """\(\d+\)""".toRegex()
        for(result in keyRegex.findAll(rule!!)) {
            keys.add(result.value.removePrefix("(").removeSuffix(")").toInt())
        }

        for(key in keys) {
            if(!processedRules.keys.contains(key)) {
                return
            }
            val keyToken = "($key)"
            rule = rule.replace(keyToken, processedRules[key]!!)
        }
        rule = rule.filterNot { it.isWhitespace() }

        processedRules[key] = rule
    }

    fun matchWithRegex(): Int {
        var sum = 0
        var rule = processedRules[0]!!
        rule = rule.replace('[','(')
        rule = rule.replace(']',')')
        var ruleRegex = rule.toRegex()

        for(message in messages) {
            if(ruleRegex.matches(message)) {
                sum++
            }
        }
        return sum
    }


}