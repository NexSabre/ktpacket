package helpers

import java.nio.ByteBuffer

fun ByteArray.toInt(): Int {
    return ByteBuffer.wrap(this).int
}

fun List<Byte>.toInt(): Int {
    return this.toByteArray().toInt()
}

fun ByteArray.toBinList(): List<String> {
    val temp = this.toList().windowed(8, 8)
    return temp.map { itList ->
        val t = itList.map {
            it.toChar()
        }
        return@map t.joinToString(separator = "")
    }
}

fun ByteArray.toBin(): String {
    val temp = this.toList().windowed(8, 8)
    return temp.map { itList ->
        val t = itList.map {
            it.toChar()
        }
        return@map t.joinToString(separator = "")
    }.joinToString(separator = "")
}
