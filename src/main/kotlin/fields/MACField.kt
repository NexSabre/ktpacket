package fields

import helpers.macToLong

class MACField(name: String?, value: Long = 0) :
    Field(name, value, 0, 48) {
        fun toLong(): Long {
            return value!!
        }
    }


fun macAddr(value: String): Long {
    val regex = Regex("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})\$")
    if (regex.matches(value)) {
        return value.macToLong()
    }
    return value.toLong()
}