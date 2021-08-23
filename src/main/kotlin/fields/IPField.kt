package fields

import helpers.ipToLong

class IPField(name: String, value: String = "0.0.0.0") :
    Field(name, value.ipToLong(), "0.0.0.0".ipToLong(), 32)