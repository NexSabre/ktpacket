package tools

import org.junit.Test
import templates.Ether
import templates.IP
import templates.TCP
import kotlin.test.assertFails

class AssertTest {

    @Test
    fun `AssertEqual frames are equal`() {
        assertHexEqual(
            Ether().add(IP()).add(TCP()),
            Ether().add(IP()).add(TCP())
        )
    }

    @Test
    fun `AssertHexEqual - negative - frames should not be equal`() {
        assertFails {
            assertHexEqual(
                Ether() / IP(),
                Ether() / IP() / TCP(),
            )
        }
    }

    @Test
    fun `Test checkStructure`() {
        val frame1 = Ether().add(IP()).add(TCP())
        val frame2 = Ether().add(IP()).add(TCP())

        assert(checkStructure(frame1, frame2))
    }

    @Test
    fun `Test negative checkStructure`() {
        val frame1 = Ether().add(IP()).add(TCP())
        val frame2 = Ether().add(IP()).add(IP()).add(TCP())

        assert(!checkStructure(frame1, frame2))
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
