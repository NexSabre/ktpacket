package testutils

import BasePacket
import templates.Ether
import templates.IP
import templates.TCP

fun simpleTCPPacket(): BasePacket {
    return Ether() / IP() / TCP()
}