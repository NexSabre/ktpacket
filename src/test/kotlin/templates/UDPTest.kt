package templates

import org.junit.Test
import kotlin.test.assertEquals

class UDPTest {
    @Test
    fun `Create default UDP`() {
        val defaultUDP = UDP()
        assertEquals(
            "0035003500080000",
            defaultUDP.hex()
        )
    }

    @Test
    fun `Create custom value`() {
        assertEquals(
            "0000003400080000",
            UDP(dport = 52, sport = 0).hex()
        )
    }
}
