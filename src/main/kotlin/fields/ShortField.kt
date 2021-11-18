package fields

class ShortField(name: String, value: Int?, defaultValue: Int = 0, size: Int = 16) :
    LongField(name, value?.toLong(), defaultValue.toLong(), size)
