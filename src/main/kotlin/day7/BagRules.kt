package day7

import java.io.File

class BagRules(fileName: String) {
    val canBeContainedIn = mutableMapOf<String, MutableList<String>>()

    init {
        File(fileName).forEachLine {
            val (containingBag, contents) = it.split(" bags contain ")
            val contentsArray = contents.removeSuffix(".").split(",")
            val regex = """\s?\d+\s(.*)\sbag""".toRegex()

            for(contentDescription in contentsArray) {
                if(contentDescription == "no other bags") {
                    continue
                }
                val results = regex.find(contentDescription)
                bagCanBeContainedIn(results!!.groupValues[1], containingBag)
            }
        }
    }

    private fun bagCanBeContainedIn(bag: String, container: String) {
        if(!canBeContainedIn.containsKey(bag)) {
            canBeContainedIn[bag] = mutableListOf()
        }
        canBeContainedIn[bag]!!.add(container)
    }

    private fun nestedBagColoursWhichCanContain(bag: String, alreadyChecked: MutableSet<String> = mutableSetOf()): Set<String> {
        alreadyChecked.add(bag)
        if(canBeContainedIn[bag] != null) {
            for(container in canBeContainedIn[bag]!!) {
                alreadyChecked.addAll(nestedBagColoursWhichCanContain(container, alreadyChecked))
            }
        }
        return alreadyChecked
    }

    fun howManyBagColoursCanContain(bag: String) : Int {
        return nestedBagColoursWhichCanContain(bag).size - 1
    }
}