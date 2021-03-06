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

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.viewmodels.CountdownStatus
import com.example.androiddevchallenge.viewmodels.CountdownViewModel

@Composable
fun ActionButtons(viewModel: CountdownViewModel) {

    val status: CountdownStatus by viewModel.status
    val playAndPauseBackground: Color by animateColorAsState(if (status == CountdownStatus.RUNNING) Color.Gray else Color.Green)

    Row() {
        FloatingActionButton(
            modifier = Modifier.padding(16.dp),
            backgroundColor = playAndPauseBackground,
            onClick = {
                when (status) {
                    CountdownStatus.RUNNING -> viewModel.pauseCountdown()
                    CountdownStatus.PAUSED -> viewModel.resumeCountdown()
                    CountdownStatus.STOPPED -> viewModel.startCountdown()
                }
            },
        ) {
            if (!viewModel.isRunning()) {
                Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "")
            } else {
                Icon(imageVector = Icons.Filled.Pause, contentDescription = "")
            }
        }

        FloatingActionButton(
            modifier = Modifier.padding(16.dp),
            onClick = { viewModel.stopCountdown() },
            backgroundColor = Color.Red
        ) {
            Icon(imageVector = Icons.Filled.Stop, contentDescription = "")
        }
    }
}
