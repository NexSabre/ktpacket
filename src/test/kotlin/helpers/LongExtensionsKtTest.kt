package helpers

import org.junit.Test
import kotlin.test.assertEquals

internal class LongExtensionsKtTest {
    @Test
    fun toBin() {
        val longValue: Long = 4 // 01000
        assertEquals(
            "0100",
            longValue.toBin(zfill = 4)
        )
    }

    @Test
    fun toHex() {
        val longValue: Long = 4 // 04
        assertEquals(
            "04",
            longValue.toHex(2)
        )
    }

    @Test
    fun toMac() {
        val longValue: Long = 12
        assertEquals(
            "00:00:00:00:00:0C",
            longValue.toMac()
        )
    }
}
