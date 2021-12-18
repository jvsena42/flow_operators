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
//        collectFlow()
        viewModelScope.launch {
            sharedFlow.collect {
                delay(2000L)
                println("FLOW: The received number is $it")
            }
        }
        squareNumber(3)
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

    //Shared Flow
    private val _sharedFlow = MutableSharedFlow<Int>(5)
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun squareNumber(number: Int) {
        viewModelScope.launch {
            _sharedFlow.emit(number * number)
        }
    }
}