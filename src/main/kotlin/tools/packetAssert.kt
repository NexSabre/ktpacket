package tools

import BasePacket

fun diff(first: BasePacket, second: BasePacket): Pair<Boolean, List<String>> {
    val diff = arrayListOf<String>()
    var diffFlag = false

    val firstHexString: List<String> = first.hex().windowed(2, 2)
    val secondHexString: List<String> = second.hex().windowed(2, 2)

    firstHexString.forEachIndexed { index, element ->
        if (secondHexString[index] != element) {
            diffFlag = true
            diff.add(element)
        } else {
            diff.add("__")
        }
    }
    return Pair<Boolean, List<String>>(diffFlag, diff)
}

// TODO complete this function when print will be available
fun diffDetailed(first: BasePacket, second: BasePacket): Boolean {
    if (!checkStructure(first, second)) {
        println("Packets have different structure. Detailed we not available")
        return false
    }

    return true
}

fun checkStructure(first: BasePacket, second: BasePacket): Boolean {
    /*
    Verify that the two frames contain the same elements (but not values).
     */
    fun getLayers(packet: BasePacket): ArrayList<String> {
        val layers = arrayListOf<String>()
        var currentLayer = packet
        while (currentLayer.payload != null) {
            layers.add(currentLayer.name)
            currentLayer = currentLayer.payload!!
        }

        return layers
    }

    return getLayers(first) == getLayers(second)
}

fun assertHexEqual(first: BasePacket, second: BasePacket) {
    val diffResultPair = diff(first, second)
    assert(!diffResultPair.first) {
        println(diffResultPair.second.joinToString(" "))
    }
}
