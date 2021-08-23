package fields

import helpers.macToLong

class MACField(name: String, value: String = "00:00:00:00:00:00") :
    Field(name, value.macToLong(), "00:00:00:00:00:00".macToLong(), 48)