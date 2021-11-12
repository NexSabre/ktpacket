package helpers

import kotlin.math.pow

fun String.macToLong(): Long {
    return this.replace(":", "").toLong(16)
}

fun String.stringHexToLong(): Long {
    return this.replace("0x", "").toLong(16)
}

fun String.ipToLong(): Long {
    var powIt = 3
    val ret = this.split(".").map {
        val result = it.toDouble() * 256.0.pow(powIt)
        powIt -= powIt
        return@map result
    }
    return ret.sum().toLong()
}

fun String.binaryStringToHexString(): String {
    return this.chunked(8).joinToString("") {
        it.toLong(2).toString(16).padStart(2, '0')
    }
}
