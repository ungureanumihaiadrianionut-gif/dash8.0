
package com.example.renaultobddash

import android.content.Context
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OBDManager(private val context: Context) {
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    private var connector: BluetoothConnector? = null

    private val _rpm = MutableStateFlow(0f)
    val rpm: StateFlow<Float> = _rpm
    private val _coolant = MutableStateFlow(0f)
    val coolant: StateFlow<Float> = _coolant
    private val _boost = MutableStateFlow(0f)
    val boost: StateFlow<Float> = _boost

    fun connectBluetooth(address: String) {
        connector = BluetoothConnector(address)
        scope.launch {
            try {
                connector?.connect()
                initializeELM()
                startPolling()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun initializeELM() {
        connector?.sendCommand("ATZ")
        delay(500)
        connector?.sendCommand("ATE0")
        delay(200)
        connector?.sendCommand("0100")
    }

    private fun startPolling() {
        scope.launch {
            while (isActive) {
                try {
                    val rpmResp = connector?.queryPID("010C")
                    rpmResp?.let { _rpm.value = parseRPM(it) }
                    val tempResp = connector?.queryPID("0105")
                    tempResp?.let { _coolant.value = parseTemp(it) }
                    val mapResp = connector?.queryPID("010B")
                    mapResp?.let { _boost.value = parseMap(it) }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                delay(500)
            }
        }
    }

    fun close() { job.cancel() }

    private fun parseRPM(raw: String): Float {
        val tokens = raw.split(Regex("[^0-9A-Fa-f]+"))
        if (tokens.size >= 4) {
            val A = tokens[2].toInt(16); val B = tokens[3].toInt(16)
            return ((A*256)+B)/4f
        }
        return 0f
    }
    private fun parseTemp(raw: String): Float {
        val tokens = raw.split(Regex("[^0-9A-Fa-f]+"))
        if (tokens.size >= 3) {
            val A = tokens[2].toInt(16)
            return (A - 40).toFloat()
        }
        return 0f
    }
    private fun parseMap(raw: String): Float {
        val tokens = raw.split(Regex("[^0-9A-Fa-f]+"))
        if (tokens.size >= 3) {
            val A = tokens[2].toInt(16)
            return A.toFloat()
        }
        return 0f
    }
}
