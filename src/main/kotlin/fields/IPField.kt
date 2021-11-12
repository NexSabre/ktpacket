package fields

class IPField(name: String, value: Long = 0) :
    Field(name, value, ipAddr("0.0.0.0"), 32)
