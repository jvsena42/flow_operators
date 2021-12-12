package com.example.kotlinflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlinflow.ui.theme.KotlinFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinFlowTheme {
                val viewModel = viewModel<MainViewModel>()
                val number = viewModel.countDownFlow.collectAsState(initial = 10)
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting(number)
                }
            }
        }
    }
}

@Composable
fun Greeting(number: State<Int>) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = number.value.toString(),
            fontSize = 34.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
