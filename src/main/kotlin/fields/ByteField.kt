package fields

class ByteField(name: String, value: Int, defaultValue: Int = 0, size: Int = 8) :
    LongField(name, value.toLong(), defaultValue.toLong(), size)
