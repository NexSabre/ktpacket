package templates

import BasePacket
import fields.BitField
import fields.Field
import fields.IntField
import fields.ShortField

// TODO dataofs
// TODO chksum
// TODO options
class TCP(
    @JvmField
    var sport: Int = 20,
    @JvmField
    var dport: Int = 80,
    @JvmField
    var seq: Int = 0,
    @JvmField
    var ack: Int = 0,
    @JvmField
    var dataofs: Int = 5,
    @JvmField
    var reserved: Int = 0,
    @JvmField
    var flags: Int = 2,
    @JvmField
    var window: Int = 8192,
    @JvmField
    var chksum: Int = 0,
    @JvmField
    var urgptr: Int = 0
) : BasePacket() {
    override val name: String = "TCP"
    override val alternativeName: String = "Transmission Control Protocol"

    override fun fieldsDesc(): List<Field> {
        return listOf(
            ShortField("sport", sport, 20),
            ShortField("dport", dport, 80),
            IntField("seq", seq, 0),
            IntField("ack", ack, 0),
            BitField("dataofs", dataofs, 5, 4),
            BitField("reserved", reserved, 0, 3),
            BitField("flags", flags, 2, 9),
            ShortField("window", window, 8192),
            ShortField("chksum", chksum),
            ShortField("urgptr", urgptr)
//            Field("options", options.toLong())
        )
    }

    override fun postBuild() {
        /*
        Run this method after packets are connected, it recalculates all fields
         */
    }
}
