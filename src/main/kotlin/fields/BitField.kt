package fields

class BitField(name: String, value: Int, defaultValue: Int = 0, size: Int = 1) :
    Field(name, value.toLong(), defaultValue.toLong(), size)