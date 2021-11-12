package templates

import BasePacket
import fields.Field
import fields.MACAddr
import fields.MACField
import helpers.stringHexToLong


class Ether(
    @JvmField
    var dst: Long = MACAddr("ff:ff:ff:ff:ff:ff"),
    @JvmField
    var src: Long = MACAddr("00:00:00:00:00:00"),
    @JvmField
    var type: Int = "0x9000".stringHexToLong().toInt()
) : BasePacket() {
    override val name = "Ether"
    override val alternativeName: String = "Ethernet"

    override fun fieldsDesc() = listOf(
        MACField("dst", dst),
        MACField("src", src),
        Field("type", type?.toString().toLong(), 9000, 16),
    )

    override fun bindLayers(payloadLayer: BasePacket) {
        when (payloadLayer) {
            is IP -> {
                type = "0x800".stringHexToLong().toInt()
            }
        }
    }
}
