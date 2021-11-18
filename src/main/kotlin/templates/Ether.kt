package templates

import BasePacket
import fields.IntField
import fields.MACField
import fields.utils.macAddr

class Ether(
    @JvmField
    var dst: Long = macAddr("ff:ff:ff:ff:ff:ff"),
    @JvmField
    var src: Long = macAddr("00:00:00:00:00:00"),
    @JvmField
    var type: Int = 0x9000
) : BasePacket() {
    override val name = "Ether"
    override val alternativeName: String = "Ethernet"

    override fun fieldsDesc() = listOf(
        MACField("dst", dst),
        MACField("src", src),
        IntField("type", type, 0x9000, 16),
    )

    override fun bindLayers(payloadLayer: BasePacket) {
        when (payloadLayer) {
            is IP -> {
                type = 0x800
            }
        }
    }
}
