package helpers

import org.junit.Test
import kotlin.test.assertEquals

internal class StringExtentionsKtTest {

    @Test
    fun macToLong() {
        val mac = "00:00:00:00:00:00"
        assertEquals(
            0,
            mac.macToLong()
        )
    }

    @Test
    fun stringHexToLong() {
        val stringHex = "0x00FF"
        assertEquals(
            255,
            stringHex.stringHexToLong()
        )
    }

    @Test
    fun ipToLong() {
        val ip = "127.0.0.1"
        assertEquals(
            2130706433,
            ip.ipToLong()
        )
    }

    @Test
    fun binaryStringToHexString() {
        val binaryString = "11110000"
        assertEquals(
            "f0",
            binaryString.binaryStringToHexString()
        )
    }
}
