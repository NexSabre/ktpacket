import org.junit.Test
import testutils.simpleTCPPacket

class sendreciveTest {
    @Test
    fun `Test matchExpectedPacket`() {
        val frame = simpleTCPPacket()
        val byteArrayFrame = frame.bin().toByteArray()
        assert(
            matchExpectedPacket(frame, byteArrayFrame)
        )
    }
}