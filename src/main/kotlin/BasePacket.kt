import fields.Field
import helpers.binaryStringToHexString
import helpers.toBin

abstract class BasePacket {
    abstract val name: String
    abstract val alternativeName: String
    abstract fun fieldsDesc(): List<Field>

    var payload: BasePacket? = null

    fun toByteArray(): ByteArray {
        /*
        Convert BasePacket to the ByteArray
         */
        return this.bin().toByteArray()
    }

    fun loadByteArray(byteArray: ByteArray): BasePacket {
//        this.fieldsDesc()
        return this
    }

    operator fun div(other: BasePacket): BasePacket {
        /*
        To provide more Pythonic & Scapy approach
        Example:
            new_frame = Ether() / IP() / TCP()

        Instead of:
            Ether().add(IP()).add(TCP())
         */
        return add(other)
    }

    fun add(newPayload: BasePacket): BasePacket {
        /*
        Add a new Packet to existing one (last one) as payload
         */
        if (this.payload != null) {
            this.payload!!.add(newPayload)
        } else {
            this.bindLayers(newPayload)
            this.payload = newPayload
        }

        return this
    }

    fun field(lookupName: String): Field? {
        return this.fieldsDesc().firstOrNull {
            it.name == lookupName
        }
    }

    fun getAttr(name: String): Any {
        return this.javaClass.getField(name).get(this)
    }

    fun setAttr(name: String, value: Any) {
        this.javaClass.getField(name).set(this, value)
    }

    fun listAttr(): List<String> {
        return this.fieldsDesc().map { it.name }
    }

    fun get(lookupName: String): BasePacket? {
        /*
        Try to get specific element from the frame.
        Look into payload and when the name will be
        equal, return object.
         */
        if (this.name == lookupName) {
            return this
        }

        if (this.payload == null) {
            return null
        }
        var tempPayload: BasePacket? = this.payload
        while (tempPayload != null) {
            if (lookupName.equals(tempPayload.name, ignoreCase = true)) {
                return tempPayload
            }

            if (tempPayload.payload == null) {
                return null
            }
            tempPayload = tempPayload.payload
        }
        return null
    }

    private fun calculateHeader(): String {
        postBuild()
        val framesHex = arrayListOf<String>()
        framesHex.add(fieldsDesc().joinToString(separator = "") {
            it.value!!.toBin(it.size)
        })
        if (payload != null) {
            payload!!.postBuild()
            framesHex.add(payload!!.bin())
        }
        return framesHex.joinToString("")
    }

    private fun headerLengthInBits(): Int = this.fieldsDesc().sumOf { it.size }

    fun headerLengthInBytes(bytes: Int = 8): Int = headerLengthInBits() / bytes

    fun length(): Int {
        return this.hex().length / 2
    }

    fun lengthToRightEnd(): Int {
        var sum = 0
        var currentLayer: BasePacket = this
        while (true) {
            sum += currentLayer.headerLengthInBytes()
            if (currentLayer.payload == null) break
            currentLayer = currentLayer.payload!!
        }
        return sum
    }

    open fun postBuild() {}

    fun bin(): String {
        return calculateHeader()
    }

    fun hex(): String {
        return calculateHeader().binaryStringToHexString()
    }

    open fun bindLayers(payloadLayer: BasePacket) {}

    fun show(indent: Int = 0) {
        val indention = "\t".repeat(indent)
        println("${indention}### [${name}] ###")
        fieldsDesc().forEach {
            println("${indention}${it.name}:\t${it.value}")
        }
        if (this.payload != null) {
            println("${indention}>> payload: ")
            this.payload!!.show(indent + 1)
        }
    }
}
