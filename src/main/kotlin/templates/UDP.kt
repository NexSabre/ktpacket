package templates

import BasePacket
import fields.Field
import fields.ShortField
import helpers.utils.calculateChksum

class UDP(
    var sport: Int = 53,
    var dport: Int = 53,
    var len: Int = 0,
    var chksum: Int = 0,
): BasePacket() {
    override val name: String
        get() = "UDP"
    override val alternativeName: String
        get() = this.name

    override fun fieldsDesc(): List<Field> {
        return listOf(
            ShortField("dport", dport),
            ShortField("sport", sport),
            ShortField("len", len),
            ShortField("chksum", chksum, 0)
        )
    }

    override fun postBuild() {
        len = lengthToRightEnd()
        chksum = layerChksum()
    }

    private fun layerChksum(): Int {
        // DO NOT USE this.bin() it will cause redundancy!!
        chksum = 0
        val binaryValue = fieldsDesc().map {
            it.bin()
        }.joinToString(separator = "")
        return calculateChksum(binaryValue)
    }

    private fun layer4Chksum(tcpBinary: String): Int {
        val pseudoLayer = this.field("src")?.bin() +
                this.field("dst")?.bin() +
                this.field("len")?.bin() +
                "00000000" +
                this.field("proto")?.bin()
        return calculateChksum(pseudoLayer + tcpBinary)
    }
}