package templates

import org.junit.Test
import tools.assertHexEqual
import kotlin.test.assertEquals

class TCPTest {
    @Test
    fun `Create default TCP`() {
        val defaultTCP = TCP()
        assertEquals(
            "0014005000000000000000005002200000000000",
            defaultTCP.hex()
        )
    }

    @Test
    fun `Create Ether - IP - TCP`() {
        // after connections
        // Ether - IP; type = "0x800"
        // IP - TCP; frag = 0, proto = 6
        val frame = Ether().add(IP()).add(TCP())
        val frameEther = frame.get("Ether") as Ether?
        val frameIP = frame.get("IP") as IP?

        assertEquals(
            0x800,
            frameEther?.type,
            "Type should be set to 0x800"
        )

        assertEquals(
            0,
            frameIP?.frag,
            "Frag in IP should be set to 0"
        )

        assertEquals(
            6,
            frameIP?.proto,
            "Proto in IP should be set to 6"
        )

        assertEquals(
            40,
            frameIP?.length(),
            "IP length with TCP should be 40B"
        )

        assertEquals(
            64462,
            frameIP?.chksum,
            "IP chksum should be equal"
        )

        assertEquals(
            54,
            frame.length(),
            "Length should be 54B"
        )

        assertHexEqual(
            Ether(type = 0x800).add(IP(frag = 0, proto = 6)).add(TCP()),
            frame
        )
    }

    @Test
    fun `Chksum verification for TCP in Ether IP TCP`() {
        val frame = Ether().add(IP()).add(TCP())
        assertEquals(
            "ffffffffffff000000000000080045000028000100004006fbce000000007f00000100140050000000000000000050022000107e0000",
            frame.hex()
        )
    }
}
