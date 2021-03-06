package helpers

fun Long.toMac(): String {
    return this.toHex(12).windowed(2, 2).joinToString(separator = ":").toUpperCase()
}

fun Long.toBin(zfill: Int = 0): String {
    if (zfill == 0) {
        return this.toString(2)
    }
    return this.toString(2).padStart(zfill, '0')
}

fun Long.toHex(zfill: Int = 0): String {
    if (zfill == 0) {
        return this.toString(16)
    }
    return this.toString(16).padStart(zfill, '0')
}
