package fields

import org.junit.Test
import kotlin.test.assertEquals

internal class FieldKtTest {
    @Test
    fun `Create new field - Field`() {
        val newField = Field(
            "field",
            3,
            size = 2
        )
        assertEquals(
            3,
            newField.value,
        )

        assertEquals(
            3,
            newField.maxSize
        )
    }

    @Test
    fun `Create field with null values`() {
        val newField = Field(
            "field",
            null,
        )
        assertEquals(
            0,
            newField.value,
            "Default value should override value"
        )

        newField.value = 100
        assertEquals(
            1,
            newField.value,
            "Cannot set greater value than allow size"
        )
    }

    @Test
    fun `Create field with higher than available default value`() {
        val newField = Field("field", null, defaultValue = 1000, size = 3)
        assertEquals(
            7,
            newField.value,
            "Default value should be trimmed to 2^3"
        )
    }

    @Test
    fun `Create field with null value and null default value`() {
        val newField = Field(
            "field",
            null,
            null
        )
        assertEquals(
            null,
            newField.value
        )
    }

    @Test
    fun `Convert field to bin`() {
        val newField = Field(
            "field",
            3,
            size = 4
        )
        assertEquals(
            "0011",
            newField.bin()
        )
    }

    @Test
    fun `Convert field to hex`() {
        val newField = Field(
            "field",
            4,
            size = 4
        )
        assertEquals(
            "04",
            newField.hex()
        )
    }
}