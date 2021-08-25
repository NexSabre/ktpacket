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