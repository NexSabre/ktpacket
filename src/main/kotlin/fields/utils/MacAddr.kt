package fields

import helpers.macToLong

fun macAddr(value: String): Long {
    val regex = Regex("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})\$")
    if (regex.matches(value)) {
        return value.macToLong()
    }
    return value.toLong()
}