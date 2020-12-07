package day7

import java.io.File

class BagRules(fileName: String) {
    val canBeContainedIn = mutableMapOf<String, MutableList<String>>()
    val mustContain = mutableMapOf<String, MutableList<Pair<String, Int>>>()

    init {
        File(fileName).forEachLine {
            val (containingBag, contents) = it.split(" bags contain ")
            val contentsArray = contents.removeSuffix(".").split(",")
            val regex = """\s?(\d+)\s(.*)\sbag""".toRegex()

            for(contentDescription in contentsArray) {
                if(contentDescription == "no other bags") {
                    continue
                }
                val results = regex.find(contentDescription)
                bagCanBeContainedIn(results!!.groupValues[2], containingBag)
                bagMustContain(
                        containingBag,
                        results!!.groupValues[1].toInt(),
                        results!!.groupValues[2]
                )
            }
        }
    }

    private fun bagCanBeContainedIn(bag: String, container: String) {
        if(!canBeContainedIn.containsKey(bag)) {
            canBeContainedIn[bag] = mutableListOf()
        }
        canBeContainedIn[bag]!!.add(container)
    }

    private fun bagMustContain(bag:String, numberOfBags:Int, containedBag:String) {
        if(!mustContain.containsKey(bag)) {
            mustContain[bag] = mutableListOf()
        }
        mustContain[bag]!!.add(Pair(containedBag, numberOfBags))
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

    fun howManyBagsMustContain(bag: String, recursion: Boolean = false): Int {
        if(!mustContain.keys.contains(bag)) {
            return 1
        }
        var required = if(recursion) 1 else 0

        for(requirement in mustContain[bag]!!) {
            required += (requirement.second * howManyBagsMustContain(requirement.first, recursion = true))

        }
        return required
    }
}