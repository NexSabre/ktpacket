package fields

import helpers.ipToLong

class IPField(name: String, value: Long = 0) :
    Field(name, value, ipAddr("0.0.0.0"), 32)


fun ipAddr(value: String): Long {
    val regex = Regex("((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|\$)){4}")
    if (regex.matches(value)) {
        return value.ipToLong()
    }
    return value.toLong()
}