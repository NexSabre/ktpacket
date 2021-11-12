package helpers

fun ByteArray.toBin(size: Int = 8, step: Int = 8): String {
    val temp = this.toList().windowed(size, step)
    return temp.map { itList ->
        val t = itList.map {
            it.toChar()
        }
        return@map t.joinToString(separator = "")
    }.joinToString(separator = "")
}
