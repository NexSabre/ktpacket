package templates

import BasePacket
import fields.*
import helpers.toBinList
import helpers.utils.calculateChksum


// TODO ihl
// TODO options
class IP(
    @JvmField
    var version: Int = 4,
    var ihl: Int = 5,
    var tos: Int = 0,
    var len: Int? = 20,
    var id: Int = 1,
    var flags: Int = 0,
    var frag: Int = 0,
    var ttl: Int = 64,
    var proto: Int = 0,
    var chksum: Int = 0,
    var src: String = "0.0.0.0",
    var dst: String = "127.0.0.1",
    var options: Int = 0,
) : BasePacket() {
    override val name = "IP"
    override val alternativeName: String = "IPv4"

    override fun fieldsDesc(): List<Field> {
        return listOf(
            BitField("version", version, 4, 4),
            BitField("ihl", ihl, 5, size = 4),
            ByteField("tos", tos),
            ShortField("len", len),
            ShortField("id", id, 1),
            BitField("flags", flags, 0, size = 3),
            BitField("frag", frag, 0, 13),
            ByteField("ttl", ttl, 64),
            ByteField("proto", proto, 0),
            ShortField("chksum", chksum, 0),
            IPField("src", src),
            IPField("dst", dst),
//        Field("options", options.toLong(), 0, if (ihl == 0) 5 else ihl * 4 - 20)
        )
    }

    override fun postBuild() {
        /*
        Run this method after packets are connected, it recalculates all fields
         */
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

    private fun layer4Chksum(nextLayerBinary: BasePacket): Int {
        val pseudoLayer = this.field("src")?.bin() +
                this.field("dst")?.bin() +
                nextLayerBinary.headerLengthInBytes().toLong().toBinList(16) +
                "00000000" +
                this.field("proto")?.bin()
        return calculateChksum(pseudoLayer + nextLayerBinary.bin())
    }

    override fun bindLayers(payloadLayer: BasePacket) {
        when (payloadLayer) {
            is TCP -> {
                frag = 0
                proto = 6
                payloadLayer.chksum = layer4Chksum(payloadLayer)
            }
            is UDP -> {
                frag = 0
                proto = 17
                payloadLayer.chksum = layer4Chksum(payloadLayer)
            }
            is IP -> {
                frag = 0
                proto = 4
                payloadLayer.chksum = layer4Chksum(payloadLayer)
            }
        }
    }
}
