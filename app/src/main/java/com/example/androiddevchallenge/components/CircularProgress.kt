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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.viewmodels.CountdownViewModel

@Composable
fun CircularProgress(viewModel: CountdownViewModel) {
    val remainingTime: Long by viewModel.remainingTime
    val startTime: Long by viewModel.startTime
    val progress by animateFloatAsState(
        targetValue = if (!(remainingTime.toFloat() / startTime).isNaN()) remainingTime.toFloat() / startTime else 1F,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(vertical = 48.dp)
    ) {

        CircularProgressIndicator(
            progress = progress,
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
                .padding(16.dp),
            color = MaterialTheme.colors.primary,
            strokeWidth = 10.dp
        )
        Text(
            text = remainingTime.toString(),
            style = TextStyle(fontSize = 24.sp)
        )
    }
}
