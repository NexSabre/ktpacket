package templates

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class UDPTest {
    @Test
    fun `Create default UDP`() {
        val defaultUDP = UDP()
        assertEquals(
            "0035003500080000",
            defaultUDP.hex()
        )
    }
}