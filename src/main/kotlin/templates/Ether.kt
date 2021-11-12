package templates

import BasePacket
import fields.Field
import fields.MACField
import fields.macAddr
import helpers.stringHexToLong

class Ether(
    @JvmField
    var dst: Long = macAddr("ff:ff:ff:ff:ff:ff"),
    @JvmField
    var src: Long = macAddr("00:00:00:00:00:00"),
    @JvmField
    var type: Int = "0x9000".stringHexToLong().toInt()
) : BasePacket() {
    override val name = "Ether"
    override val alternativeName: String = "Ethernet"

    override fun fieldsDesc() = listOf(
        MACField("dst", dst),
        MACField("src", src),
        Field("type", type.toLong(), 9000, 16),
    )

    override fun bindLayers(payloadLayer: BasePacket) {
        when (payloadLayer) {
            is IP -> {
                type = "0x800".stringHexToLong().toInt()
            }
        }
    }
}
