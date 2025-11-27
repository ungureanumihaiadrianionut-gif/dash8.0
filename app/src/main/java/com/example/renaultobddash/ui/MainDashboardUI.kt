
package com.example.renaultobddash.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainDashboardUI() {
    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        Text("Renault OBD Dash - Demo UI")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Connect to an ELM327 dongle to view live gauges.")
    }
}
