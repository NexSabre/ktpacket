package tools

import org.junit.Test
import templates.Ether
import templates.IP
import templates.TCP

class AssertTest {

    @Test
    fun `AssertEqual frames are equal`() {
        val frame1 = Ether().add(IP()).add(TCP())
        val frame2 = Ether().add(IP()).add(TCP())

        assertHexEqual(
            frame1,
            frame2
        )
    }

    @Test
    fun `Test checkTypes`() {
        val frame1 = Ether().add(IP()).add(TCP())
        val frame2 = Ether().add(IP()).add(TCP())

        assert(checkTypes(frame1, frame2))
    }

    @Test
    fun `Test negative checkTypes`() {
        val frame1 = Ether().add(IP()).add(TCP())
        val frame2 = Ether().add(IP()).add(IP()).add(TCP())

        assert(!checkTypes(frame1, frame2))
    }

    @Test
    fun `Test diffDetailed`() {
    }

    @Test
    fun `Test negative diffDetailed`() {
        val frame1 = Ether().add(IP()).add(TCP())
        val frame2 = Ether().add(IP()).add(IP()).add(TCP())

        assert(!diffDetailed(frame1, frame2))
    }
}
