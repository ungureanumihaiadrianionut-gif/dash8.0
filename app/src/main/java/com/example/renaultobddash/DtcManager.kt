
package com.example.renaultobddash

class DtcManager(private val connector: BluetoothConnector) {
    suspend fun readDTCs(): List<String> {
        val raw = connector.queryPID("03") ?: return emptyList()
        val tokens = raw.split(Regex("[^0-9A-Fa-f]+"))
        val dtcs = mutableListOf<String>()
        // simplified parsing
        for (i in 0 until tokens.size-1) {
            val t = tokens[i]
            if (t.equals("43", true) && i+2 < tokens.size) {
                dtcs.add(tokens[i+1] + tokens[i+2])
            }
        }
        return dtcs
    }
    suspend fun clearDTCs(): Boolean { connector.sendCommand("04"); return true }
}
