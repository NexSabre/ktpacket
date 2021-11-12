package templates

import BasePacket
import fields.Field
import fields.MACField
import helpers.stringHexToLong


class Ether(
    @JvmField
    var dst: Long = MACField(name="dst", value="ff:ff:ff:ff:ff:ff").toLong(),
    @JvmField
    var src: Long = MACField(name="src", value="00:00:00:00:00:00").toLong(),
    @JvmField
    var type: Int = "0x9000".stringHexToLong().toInt()
) : BasePacket() {
    override val name = "Ether"
    override val alternativeName: String = "Ethernet"

    override fun fieldsDesc() = listOf(
        MACField("dst", dst.toString()),
        MACField("src", src.toString()),
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
