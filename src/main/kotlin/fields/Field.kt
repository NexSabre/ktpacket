package fields

import helpers.binaryStringToHexString
import kotlin.math.pow

fun maxValue(size: Int): Long = 2.0.pow(size).toLong() - 1


open class Field(val name: String? = "", value: Long? = 0, val defaultValue: Long? = 0, val size: Int = 1) {
    /*
    Base class for fields
     */
    var maxSize: Long = maxValue(size)
    var value: Long? = setDefaultValueOrNull(value)
        set(_value) {
            field = setDefaultValueOrNull(_value)
        }

    private fun setDefaultValueOrNull(_value: Long?): Long? {
        var retVal: Long? = _value
        if (_value == null) {
            retVal = defaultValue
        }

        // trim to max size if extends
        return retVal?.coerceIn(0..maxSize)
    }

    override fun toString(): String {
        return value.toString()
    }

    fun bin(): String? {
        return value?.toString(2)?.padStart(size, '0')
    }

    fun hex(): String? {
        return value?.toString(2)?.padStart(size, '0')?.binaryStringToHexString()
    }
}
