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
package com.example.androiddevchallenge.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class CountdownViewModel : ViewModel() {
    var startTime = mutableStateOf(60L)
    val remainingTime = mutableStateOf(60L)

    var running = mutableStateOf(CountdownStatus.STOPPED)

    private fun isRunning() = running.value == CountdownStatus.RUNNING

    private fun countdown(time: Long): Flow<Long> = flow {
        for (second in (time) downTo 0) {
            if (isRunning()) {
                delay(1000L)
                emit(second)
            }
        }
    }

    private fun startCountdown() {
        running.value = CountdownStatus.RUNNING
        viewModelScope.launch {
            countdown(startTime.value).collect { remainingSeconds -> remainingTime.value = remainingSeconds }
        }
    }

    fun stopCountdown() {
        running.value = CountdownStatus.STOPPED
    }

    private fun pauseCountdown() {
        running.value = CountdownStatus.PAUSED
    }

    fun setStartTime(it: String) {
        if (it.isEmpty()) {
            startTime.value = 0L
        } else {
            startTime.value = it.toLong()
        }
    }

    fun toggleStartCountdown() {
        when (running.value) {
            CountdownStatus.RUNNING -> pauseCountdown()
            CountdownStatus.PAUSED -> startCountdown()
            CountdownStatus.STOPPED -> startCountdown()
        }
    }
}

enum class CountdownStatus {
    RUNNING, PAUSED, STOPPED
}
