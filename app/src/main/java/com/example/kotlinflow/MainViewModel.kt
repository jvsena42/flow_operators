package com.example.kotlinflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val countDownFlow = flow {
        for (i in 10 downTo 1) {
            emit(i)
            delay(1000L)
        }
    }

    init {
        collectFlow()
    }

    private fun collectFlow() {
        viewModelScope.launch {
            countDownFlow.collect { time ->
                println("The current time is $time")
            }

            //Chanel operators

            // accumulator -> sum of previous values, value -> present result
           /* val reduceResult = countDownFlow.reduce { accumulator, value ->
                accumulator + value
            }*/

            // is like reduce, but I need to provide a start value
            /*val foldResult = countDownFlow.fold(100) { accumulator, value ->
                accumulator + value
            }*/
        }
    }

    //State Flow
    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()

    fun incrementCounter() {
        _stateFlow.value += 1
    }
}