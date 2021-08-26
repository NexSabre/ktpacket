package testutils

import BasePacket
import templates.Ether
import templates.IP
import templates.TCP
import templates.UDP

fun simpleTCPPacket(): BasePacket {
    return Ether() / IP() / TCP()
}

fun simpleUDPPacket(): BasePacket {
    return Ether() / IP() / UDP()
}

fun simpleIPinIPPacket(): BasePacket {
    return Ether() / IP() / IP() / TCP()
}
