package com.example.hoods

import android.os.CountDownTimer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp


@Composable
fun CountdownTimer() {
    var timerRunning by remember { mutableStateOf(false) }
    var timeRemaining by remember { mutableStateOf(0L) }
    var showResend by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(CornerSize(4.dp))),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
//                .background(color = colorResource(id = R.color.pnp_blue2_light))
        ) {
            if (timeRemaining > 0) {
                Text(
                    text = " $timeRemaining seconds",
                )
            }
        }
        if (!timerRunning) {
            Button(onClick = {
                timerRunning = true
                timeRemaining = 5L
            }) {
                Text("Resend")
            }
        }

        LaunchedEffect(timerRunning) {
            if (timerRunning) {
                object : CountDownTimer(timeRemaining * 1000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        timeRemaining = millisUntilFinished / 1000
                    }

                    override fun onFinish() {
                        timerRunning = false
                        showResend = true
                    }
                }.start()
            }
        }
    }
}