package helpers

import org.junit.Test
import kotlin.test.assertEquals

internal class LongExtensionsKtTest {
    @Test
    fun toBin() {
        val longValue: Long = 4  // 01000
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
}