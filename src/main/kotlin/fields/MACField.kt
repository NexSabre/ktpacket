package fields

import helpers.macToLong

class MACField(value: String = "00:00:00:00:00:00", name: String?) :
    Field(name, value.macToLong(), "00:00:00:00:00:00".macToLong(), 48) {
        fun toLong(): Long {
            return value!!
        }
    }


fun MACAddr(value: String): Long {
    val regex = Regex("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})\$")
    if (regex.matches(value)) {
        return value.macToLong()
    }
    return value.toLong()
}