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

    @Test
    fun `Test simple_udp_packet`() {
        val simpleUDFrame = simpleUDPPacket()
        assertEquals(
            "ffffffffffff00000000000008004500001c000100004011fbcf000000007f0000010035003500088073",
            simpleUDFrame.hex()
        )
    }

    @Test
    fun `Test simple ip in ip packet`() {
        val simpleIPinIPFrame = simpleIPinIPPacket()
        assertEquals(
            "ffffffffffff00000000000008004500003c000100004004fbbc000000007f00000145000028000100004006fbce000000007f00000100140050000000000000000050022000107e0000",
            simpleIPinIPFrame.hex()
        )
    }
}
