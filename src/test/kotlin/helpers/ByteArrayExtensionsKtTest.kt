package helpers

import org.junit.Test
import kotlin.test.assertEquals

class ByteArrayExtensionsKtTest {
    @Test
    fun `Test ByteArray_toBin`() {
        assertEquals(
            0,
            "00000010".toByteArray().toBin().slice(0..4).toInt(radix = 2)
        )

        assertEquals(
            2,
            "00000010".toByteArray().toBin().slice(3..7).toInt(radix = 2)
        )
    }
}