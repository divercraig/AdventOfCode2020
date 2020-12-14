package day14

import java.io.File

@ExperimentalStdlibApi
class DockingComputer(fileName: String) {
    val instructions = mutableListOf<Pair<String, String>>()
    val memory = mutableMapOf<Long, Long>()
    var currentMask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
    val memoryRegex = """^mem\[(\d+)\]$""".toRegex()

    init {
        File(fileName).forEachLine {
            val parts =  it.split(" = ")
            instructions.add(Pair(parts[0], parts[1]))
        }
    }

    internal fun applyMask(value: Long, mask:String): Long {
        var chars = CharArray(36) {'0'}
        var binaryValue = value.toString(2)
        for (i in 0..binaryValue.length-1) {
            chars[35-i] = binaryValue[binaryValue.length - i - 1]
        }

        for(i in 0..35) {
            if(mask[i] == '1' || mask[i] == '0') {
                chars[i] = mask[i]
            }
        }
        return chars.concatToString().toLong(2)
    }

    internal fun deriveMemoryAddresses(input: CharArray): List<Long> {
        var output = mutableListOf<Long>()

        if(!input.contains('X')) {
            output.add(input.concatToString().toLong(2))
        } else {
            val index = input.indexOf('X')
            val zero = input.copyOf()
            val one = input.copyOf()
            one[index] = '1'
            zero[index] = '0'
            output.addAll(deriveMemoryAddresses(zero))
            output.addAll(deriveMemoryAddresses(one))
        }

        return output
    }

    internal fun applyMemoryAddressMask(address: Long?, mask:String): List<Long> {
        var memoryAddressString = CharArray(36) {'0'}
        var binaryAddress = address?.toString(2)
        if (binaryAddress != null) {
            for(i in 0..binaryAddress.length-1) {
                memoryAddressString[35-i] = binaryAddress[binaryAddress.length -i -1]
            }
        }

        for(i in 0..35) {
            if(mask[i] == '1' || mask[i] == 'X') {
                memoryAddressString[i] = mask[i]
            }
        }

        return deriveMemoryAddresses(memoryAddressString)
    }

    fun runProgram(): Long {
        for(instruction in instructions) {
            if(instruction.first == "mask" ) {
                currentMask = instruction.second
            } else {
                val memLocation = memoryRegex.find(instruction.first)?.groupValues?.get(1)?.toInt()
                val memoryValue = applyMask(instruction.second.toLong(), currentMask)
                if (memLocation != null) {
                    memory.put(memLocation.toLong(), memoryValue)
                }
            }
        }

        return memory.values.reduce { acc, l -> acc + l }
    }

    fun runProgramV2(): Long {
        for(instruction in instructions) {
            if(instruction.first == "mask") {
                currentMask = instruction.second
            } else {
                val memoryValue = instruction.second.toLong()
                val memoryLocation = memoryRegex.find(instruction.first)?.groupValues?.get(1)?.toLong()
                val memoryAddresses = applyMemoryAddressMask(memoryLocation, currentMask)
                for(address in memoryAddresses) {
                    memory[address] = memoryValue
                }
            }
        }
        return memory.values.reduce { acc, l -> acc + l }
    }
}