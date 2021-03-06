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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.viewmodels.CountdownViewModel

@Composable
fun CountdownScreen(viewModel: CountdownViewModel = viewModel()) {

    var startTime by remember { mutableStateOf("60") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        CircularProgress(viewModel = viewModel)
        TextField(
            modifier = Modifier.padding(16.dp),
            value = startTime,
            onValueChange = {
                startTime = it
                viewModel.setRemainingTime(startTime)
                viewModel.setStartTime(startTime)
            },
            label = { Text("Seconds") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            enabled = viewModel.isStopped()
        )
        ActionButtons(viewModel = viewModel)
    }
}

@Preview()
@Composable
fun LightPreview() {
    CountdownScreen()
}
