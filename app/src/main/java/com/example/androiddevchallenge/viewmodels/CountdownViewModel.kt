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

import android.os.CountDownTimer
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CountdownViewModel : ViewModel() {
    var startTime = mutableStateOf(60L)
    val remainingTime = mutableStateOf(60L)

    var status = mutableStateOf(CountdownStatus.STOPPED)

    var countdown: CountDownTimer? = null

    fun isRunning() = status.value == CountdownStatus.RUNNING
    fun isStopped() = status.value == CountdownStatus.STOPPED
    fun isPaused() = status.value == CountdownStatus.PAUSED

    fun startCountdown() {
        status.value = CountdownStatus.RUNNING
        countdown = object : CountDownTimer(remainingTime.value * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTime.value = millisUntilFinished / 1000
            }

            override fun onFinish() {
                stopCountdown()
            }
        }
        countdown?.start()
    }

    fun stopCountdown() {
        status.value = CountdownStatus.STOPPED
        countdown?.cancel()
        remainingTime.value = startTime.value
    }

    fun pauseCountdown() {
        status.value = CountdownStatus.PAUSED
        countdown?.cancel()
    }

    fun resumeCountdown() {
        status.value = CountdownStatus.RUNNING
        startCountdown()
    }

    fun setStartTime(it: String) {
        if (it.isEmpty()) {
            startTime.value = 0L
        } else {
            startTime.value = it.toLong()
        }
    }

    fun setRemainingTime(it: String) {
        if (it.isEmpty()) {
            remainingTime.value = 0L
        } else {
            remainingTime.value = it.toLong()
        }
    }
}

enum class CountdownStatus {
    RUNNING, PAUSED, STOPPED
}
