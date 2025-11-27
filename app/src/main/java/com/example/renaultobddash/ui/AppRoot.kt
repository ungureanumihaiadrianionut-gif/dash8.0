
package com.example.renaultobddash.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable

@Composable
fun AppRoot() {
    MaterialTheme {
        Surface { MainDashboardUI() }
    }
}
