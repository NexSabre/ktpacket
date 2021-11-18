package fields

class MACField(name: String?, value: Long = 0) :
    LongField(name, value, 0, 48) {
    fun toLong(): Long {
        return value!!
    }
}
