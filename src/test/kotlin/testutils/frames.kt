package testutils

import org.junit.Test
import kotlin.test.assertEquals


class FramesTest {
    @Test
    fun `Test simple_tcp_packet`() {
        val simpleTCPFrame = simpleTCPPacket()
        assertEquals(
            "ffffffffffff000000000000080045000028000100004006fbce000000007f00000100140050000000000000000050022000107e0000",
            simpleTCPFrame.hex()
        )
    }
}