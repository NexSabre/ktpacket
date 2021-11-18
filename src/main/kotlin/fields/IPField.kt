package fields

import fields.utils.ipAddr

class IPField(name: String, value: Long = 0) :
    LongField(name, value, ipAddr("0.0.0.0"), 32)
