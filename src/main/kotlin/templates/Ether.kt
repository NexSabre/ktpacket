package templates

import BasePacket
import fields.Field
import fields.MACField
import helpers.stringHexToLong

class Ether(
    var dst: String = "ff:ff:ff:ff:ff:ff",
    var src: String = "00:00:00:00:00:00",
    var type: String = "0x9000"
) : BasePacket() {
    override val name = "Ether"
    override val alternativeName: String = "Ethernet"

    override fun fieldsDesc() = listOf(
        MACField("dst", dst),
        MACField("src", src),
        Field("type", type.stringHexToLong(), 9000, 16),
    )

    override fun bindLayers(payloadLayer: BasePacket) {
        when (payloadLayer) {
            is IP -> {
                type = "0x800"
            }
        }
    }
}
