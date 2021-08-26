import templates.Ether
import templates.IP
import templates.TCP
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

fun main(args: Array<String>) {
    val ether = Ether(
        "00:00:00:00:00:00",
        "ff:ff:ff:ff:ff:ff",
        "0x9000"
    ) / IP() / TCP()
//    println(ether.dst)
//    println(ether.bin())
//    println(ether.hex())

    val socket = DatagramSocket(8888)
    socket.broadcast = true
    println(ether.bin())
    println(ether.bin().toByteArray())
    val packet = DatagramPacket(ByteArray(2048), ByteArray(2048).size)
    socket.send(
        DatagramPacket(
            ether.bin().toByteArray(),
            ether.bin().toByteArray().size,
            InetAddress.getByName("127.0.0.1"),
            8888
        )
    )
    socket.receive(packet)
    println("data:" + String(packet.data).takeWhile { it.toInt() != 0 })
    println(String(packet.data).takeWhile { it.toInt() != 0 } == ether.bin())
}


