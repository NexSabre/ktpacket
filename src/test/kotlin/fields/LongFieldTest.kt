package fields

import fields.utils.ipAddr
import fields.utils.macAddr
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class LongFieldTest {
    @Test
    fun `Create new field - Field`() {
        val newLongField = LongField(
            "field",
            3,
            size = 2
        )
        assertEquals(
            3,
            newLongField.value,
        )

        assertEquals(
            3,
            newLongField.maxSize
        )
    }

    @Test
    fun `Create field with null values`() {
        val newLongField = LongField(
            "field",
            null,
        )
        assertEquals(
            0,
            newLongField.value,
            "Default value should override value"
        )

        newLongField.value = 100
        assertEquals(
            1,
            newLongField.value,
            "Cannot set greater value than allow size"
        )
    }

    @Test
    fun `Create field with higher than available default value`() {
        val newLongField = LongField("field", null, defaultValue = 1000, size = 3)
        assertEquals(
            7,
            newLongField.value,
            "Default value should be trimmed to 2^3"
        )
    }

    @Test
    fun `Create field with null value and null default value`() {
        val newLongField = LongField(
            "field",
            null,
            null
        )
        assertEquals(
            null,
            newLongField.value
        )
    }

    @Test
    fun `Convert field to bin`() {
        val newLongField = LongField(
            "field",
            3,
            size = 4
        )
        assertEquals(
            "0011",
            newLongField.bin()
        )
    }

    @Test
    fun `Convert field to hex`() {
        val newLongField = LongField(
            "field",
            4,
            size = 4
        )
        assertEquals(
            "04",
            newLongField.hex()
        )
    }

    @Test
    fun `Check MACAddr`() {
        val macAddress = macAddr("aa:bb:cc:aa:bb:cc")
        assertEquals(
            187723569347532,
            macAddress
        )
    }

    @Test
    fun `Check MACAddr -- Negative -- Bad MACAddress`() {
        assertFailsWith<NumberFormatException>(
            block = { macAddr("aa:bb:ccaa:bb:cc") }
        )
    }

    @Test
    fun `Check ipAddr`() {
        val ipAddress = ipAddr("127.0.0.1")
        assertEquals(
            2130706433,
            ipAddress
        )
    }
}
