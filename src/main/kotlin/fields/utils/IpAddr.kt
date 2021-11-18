package fields.utils

import helpers.ipToLong

fun ipAddr(value: String): Long {
    val regex = Regex("((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|\$)){4}")
    if (regex.matches(value)) {
        return value.ipToLong()
    }
    return value.toLong()
}
