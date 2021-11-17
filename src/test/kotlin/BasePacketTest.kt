import fields.macAddr
import org.junit.Test
import templates.Ether
import templates.IP
import templates.TCP
import kotlin.test.assertEquals

class BasePacketTest {
    private val mac = "00:00:00:00:00:00"

    @Test
    fun `Test get() on example - existing IP`() {
        val frame = Ether(macAddr(mac), macAddr(mac), 0x9000).add(IP())
        val ip: BasePacket? = frame.get("IP")
        assert(ip != null)
        assertEquals(
            34,
            frame.length(),
            "Frames should be equal"
        )
    }

    @Test
    fun `Test get() on example - missing TCP`() {
        val frame = Ether(macAddr(mac), macAddr(mac), 0x9000).add(IP())
        val tcp: BasePacket? = frame.get("TCP")
        assert(tcp == null)
    }

    @Test
    fun `Test show function`() {
        val frame = Ether(macAddr(mac), macAddr(mac), 0x9000).add(IP()).add(TCP())

        frame.show()
    }

    @Test
    fun `Test div operator`() {
        val frame = Ether(macAddr(mac), macAddr(mac), 0x9000) / IP() / TCP()
        assertEquals(
            "000000000000000000000000080045000028000100004006fbce000000007f00000100140050000000000000000050022000107e0000",
            frame.hex()
        )
    }

    @Test
    fun `Test convert Ether to the ByteArray`() {
        assert(Ether().toByteArray().isNotEmpty())
    }

    @Test
    fun `Test loadBytes of IP into IP packet`() {
        val sampleIP = IP(version = 8)
        val loadedIP = IP().loadByteArray(
            sampleIP.toByteArray()
        )
        assertEquals(
            sampleIP.hex(),
            loadedIP.hex(),
            "Objects should be equal"
        )
    }

    @Test
    fun `Test loadBytes of Ether into Ether packet with changed mac address`() {
        val sampleEther = Ether(dst = macAddr("ff:ff:ff:ff:ff:ff"), type = 0x8888)
        val loadedEther = Ether().loadByteArray(
            sampleEther.toByteArray()
        )
        assertEquals(
            sampleEther.hex(),
            loadedEther.hex(),
            "Objects should be equal"
        )
    }

    @Test
    fun `Test loadBytes of simpleEtherPacket`() {
        val simpleTCPPacket = Ether(0, 0, 0x8888) / IP() / TCP(0, 0)
        val packetSkeleton = Ether() / IP() / TCP()

        assertEquals(
            simpleTCPPacket.hex(),
            packetSkeleton.loadByteArray(simpleTCPPacket.toByteArray()).hex(),
            "Objects should be equal"
        )
    }
}
