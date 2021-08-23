package helpers.utils

fun calculateChksum(binary: String): Int {
    val listOfIntegers = binary.chunked(16).map {
        it.toInt(2)
    }

    return listOfIntegers.reduce { acc, l ->
        var totalValue = acc + l
        if (totalValue.shr(16) == 1) {
            totalValue = (totalValue.shr(16)) + (totalValue.and(0xFFFF))
        }
        totalValue
    }.xor(0xFFFF)
}
