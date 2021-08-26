import java.net.DatagramPacket
import java.net.DatagramSocket

fun matchExpectedPacket(expectedPacket: BasePacket, receivedPacket: ByteArray): Boolean {
    return expectedPacket.bin() == String(receivedPacket).takeWhile { it.toInt() != 0 }
}

fun matchExpectedPacket(expectedPacket: BasePacket, receivedPacket: BasePacket): Boolean {
    return expectedPacket.bin() == receivedPacket.bin()
}

//fun sendAndReceiveAtPort(sport: Int, dport: Int?, broadcast: Boolean = true): Boolean {
//    var _dport = dport
//    if (dport == null) {
//        _dport = sport
//    }
//
//    val socket = DatagramSocket(sport)
//    socket.broadcast = broadcast
//
//
//}

class Socket(val portNumber: Int, val broadcast: Boolean = true) {
    fun send(packet: ByteArray) {
        this._send(packet)
    }

    fun send(packet: BasePacket) {
        this._send(packet.bin().toByteArray())
    }

    private fun _send(packet: ByteArray, port: Int? = null) {
        val socket = DatagramSocket(port ?: portNumber)
        socket.broadcast = broadcast

        socket.send(
            DatagramPacket(packet, packet.size)
        )
    }
}