/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.viewmodels.CountdownStatus
import com.example.androiddevchallenge.viewmodels.CountdownViewModel

@Composable
fun CountdownScreen(viewModel: CountdownViewModel = viewModel()) {
    val remainingTime: Long by viewModel.remainingTime
    val startTime: Long by viewModel.startTime
    val status: CountdownStatus by viewModel.running

    val progress by animateFloatAsState(
        targetValue = remainingTime.toFloat() / startTime,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(vertical = 56.dp)
        ) {

            CircularProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .padding(16.dp),
                color = MaterialTheme.colors.primary,
                strokeWidth = 10.dp
            )
            Text(
                text = remainingTime.toString(),
                style = TextStyle(fontSize = 24.sp)
            )
        }
        TextField(
            modifier = Modifier.padding(16.dp),
            value = startTime.toString(),
            onValueChange = { viewModel.setStartTime(it) },
            label = { Text("Seconds") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            enabled = status != CountdownStatus.RUNNING
        )
        FloatingActionButton(

            onClick = { viewModel.toggleStartCountdown() },
        ) {
            if (status != CountdownStatus.RUNNING) {
                Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "")
            } else {
                Icon(imageVector = Icons.Filled.Pause, contentDescription = "")
            }
        }
    }
}

@Preview()
@Composable
fun LightPreview() {
    CountdownScreen()
}
