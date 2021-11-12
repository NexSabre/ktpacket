package templates

import fields.macAddr
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

internal class EtherTest {
    private val mac = "00:00:00:00:00:00"
    private lateinit var ether: Ether

    @Before
    fun setUp() {
        ether = Ether(macAddr(mac), macAddr(mac))
    }

    @Test
    fun `Default init for Ether`() {
        val defaultEther = Ether()
        assertEquals(
            "FFFFFFFFFFFF0000000000009000",
            defaultEther.hex().toUpperCase(),
            "Hex values should be equal"
        )
    }

    @Test
    fun `Check getHex`() {
        assertEquals(
            ether.hex(),
            "0000000000000000000000009000",
            "Values should be equal"
        )
    }

    @Test
    fun `Check getBin`() {
        assertEquals(
            ether.bin(),
            "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001" +
                    "001000000000000"
        )
    }

    @Test
    fun `Add Ether to Ether`() {
        val innerEther = Ether(macAddr(mac), macAddr(mac), 0x8000)
        ether.add(
            innerEther
        )

        assertEquals(
            ether.payload,
            innerEther,
            "innerEther should be a payload for the initial Ether"
        )

        assertEquals(
            ether.hex(),
            "00000000000000000000000090000000000000000000000000008000"
        )

        assertEquals(
            ether.bin(),
            "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001" +
                    "00100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" +
                    "00000000000000001000000000000000"
        )
    }

    @Test
    fun `Add Ether to Ether, two times`() {
        val innerEther = Ether(macAddr(mac), macAddr(mac), 0x8000)
        val innerEther2 = Ether(macAddr(mac), macAddr(mac), 0x8000)
        ether.add(
            innerEther
        )
        ether.add(
            innerEther2
        )

        assertEquals(
            ether.hex(),
            "000000000000000000000000900000000000000000000000000080000000000000000000000000008000"
        )
    }

    @Test
    fun `Change type after initialization`(){
        val ether = Ether(macAddr(mac), macAddr(mac), 0x8000)
        val etherHex = ether.hex()
        ether.type = 0x9000
        assertNotEquals(
            etherHex,
            ether.hex(),
            "Hex values should be different. Type value was changed from default 8000 -> 9000"
        )
    }

    @Test
    fun `Verify length of the Ether packet`() {
        assertEquals(
            14,
            ether.length()
        )
    }
}