package templates

import BasePacket
import fields.LongField
import fields.ShortField

class UDP(
    @JvmField
    var sport: Int = 53,
    @JvmField
    var dport: Int = 53,
    @JvmField
    var len: Int = 0,
    @JvmField
    var chksum: Int = 0,
) : BasePacket() {
    override val name: String = "UDP"
    override val alternativeName: String = this.name

    override fun fieldsDesc(): List<LongField> {
        return listOf(
            ShortField("sport", sport),
            ShortField("dport", dport),
            ShortField("len", len),
            ShortField("chksum", chksum)
        )
    }

    override fun postBuild() {
        len = lengthToRightEnd()
    }
}
