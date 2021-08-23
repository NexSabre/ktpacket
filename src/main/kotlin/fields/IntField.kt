package fields

class IntField(name: String, value: Int, defaultValue: Int = 0, size: Int = 32) :
    Field(name, value.toLong(), defaultValue.toLong(), size)