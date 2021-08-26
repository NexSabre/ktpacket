import org.pcap4j.core.PcapHandle
import org.pcap4j.core.PcapNetworkInterface
import org.pcap4j.core.Pcaps
import templates.Ether
import templates.IP
import templates.UDP
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    val ether = Ether() / IP(dst = "127.0.0.1") / UDP(dport = 8888)
//    println(ether.dst)
//    println(ether.bin())
//    println(ether.hex())

    val socket = DatagramSocket(8888)
    socket.broadcast = true
//    println(ether.bin())
//    println(ether.bin().toByteArray())
    val packet = DatagramPacket(ByteArray(2048), ByteArray(2048).size)
//    socket.send(
//        DatagramPacket(
//            ether.bin().toByteArray(),
//            ether.bin().toByteArray().size,
//            null
//        )
//    )
//    val s = DataOutputStream()
//    val pcap = Pca
    var address: InetAddress = InetAddress.getLocalHost()

    val nif = Pcaps.getDevByAddress(address)

    var readHandle: PcapHandle
    thread(start = true, isDaemon = false) {
        readHandle = nif.openLive(65536, PcapNetworkInterface.PromiscuousMode.NONPROMISCUOUS, 0)
        while (readHandle.isOpen) {
            val raw = readHandle.nextRawPacket

            if (raw != null) {
                println(String(raw))
//                val packet = Ether.of(ByteArraySimpleReader(raw))
//                if (packet != null)
//                    currentPacket = packet to index.incrementAndGet()
            }
        }
    }
    val sendHandle = nif.openLive(65536, PcapNetworkInterface.PromiscuousMode.NONPROMISCUOUS, 0)
    sendHandle.sendPacket(
        ether.bin().toByteArray()
    )
    sendHandle.sendPacket(
        ether.bin().toByteArray()
    )
    println(ether.bin())




    socket.receive(packet)
    println("data:" + String(packet.data).takeWhile { it.toInt() != 0 })
    println(String(packet.data).takeWhile { it.toInt() != 0 } == ether.bin())
}


