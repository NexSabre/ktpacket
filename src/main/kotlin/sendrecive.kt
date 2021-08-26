fun matchExpectedPacket(expectedPacket: BasePacket, receivedPacket: ByteArray): Boolean {
    return expectedPacket.bin() == String(receivedPacket).takeWhile { it.toInt() != 0 }
}