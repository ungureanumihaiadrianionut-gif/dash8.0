
package com.example.renaultobddash.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp

@Composable
fun GaugeComposable(label: String, value: Float, max: Float, modifier: Modifier = Modifier) {
    Box(modifier = modifier.padding(8.dp)) {
        Canvas(modifier = Modifier.aspectRatio(1f)) {
            val cx = size.width/2f
            val cy = size.height/2f
            val r = size.minDimension/2f * 0.8f
            drawCircle(alpha = 0.08f, radius = r, center = Offset(cx, cy))
            val angle = -120f + (value/max)*240f
            rotate(angle, pivot = Offset(cx, cy)) {
                drawLine(androidx.compose.ui.graphics.Color.Red, Offset(cx, cy), Offset(cx, cy - r + 10f), strokeWidth = 6f)
            }
        }
        Text(label, modifier = Modifier.padding(4.dp))
        Text(value.toInt().toString(), modifier = Modifier.padding(4.dp))
    }
}
