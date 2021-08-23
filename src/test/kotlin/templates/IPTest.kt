package templates

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals


class IPTest {
    private val mac = "00:00:00:00:00:00"
    private lateinit var ip: IP

    @Before
    fun setUp() {
        ip = IP()
    }

    @Test
    fun `Check hex`() {
        assertEquals(
            "45000014000100004000fbe8000000007f000001",
            ip.hex()
        )
    }

    @Test
    fun `Default values for IP`() {
        val defaultIP = IP()
        assertEquals(
            "45000014000100004000fbe8000000007f000001",
            defaultIP.hex(),
            "Default values should be equal"
        )
    }

    @Test
    fun `Build Ether - IP`() {
        val ether = Ether(mac, mac, "0x800")
        val frame = ether.add(IP())

        assertEquals(
            "000000000000000000000000080045000014000100004000fbe8000000007f000001",
            frame.hex(),
            "hex should be equal"
        )

    }

    @Test
    fun `Verify chksum after the postBuild process`() {
        val newIP = IP()
        newIP.postBuild()

        assertEquals(64488,
            newIP.fieldsDesc().first { it.name == "chksum" }.value,
            "chksum in fieldDesc should be equal"
        )
        assertEquals(
            64488,
            newIP.chksum,
            "chksum value in property should be equal"
        )


    }

    @Test
    fun `Verify length of the IP packet`() {
        assertEquals(
            20,
            ip.length()
        )
    }

    @Test
    fun `Verify length of the Ether - IP packet`() {
        val ether = Ether(mac, mac, "0x9000")
        val frame = ether.add(IP())

        assertEquals(
            34,
            frame.length()
        )

        val tempIp = ether.get("IP")
        assertEquals(
            20,
            tempIp?.length()
        )
    }

    @Test
    fun `Verify length of the Ether - IP - TCP packet`() {
        val ether = Ether(mac, mac, "0x9000")
        val frame = ether.add(IP()).add(TCP())

        assertEquals(
            54,
            frame.length()
        )

        val tempIp = ether.get("IP") as IP
        assertEquals(
            40,
            tempIp.length()
        )
    }

    @Test
    fun `Change value at IP`() {
        val ip = IP()
        val ipChksum = ip.chksum
        val ipHex = ip.hex()
        assertEquals(
            0,
            ip.flags,
            "Flags value should be default 0"
        )

        ip.flags = 2
        ip.postBuild()
        val ipHexAfterFlagChange = ip.hex()
        val ipChksumAfterFlagChange = ip.chksum
        assertEquals(
            2,
            ip.flags,
            "Flag value should be changed to the 2"
        )
        assertNotEquals(
            ipHexAfterFlagChange,
            ipHex
        )
        assertNotEquals(
            ipChksumAfterFlagChange,
            ipChksum
        )
    }
}
