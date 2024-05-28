package com.android.flow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.flow.ui.theme.StateFlowTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateFlowTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Observables(viewModel = viewModel, activity = this)
                }
            }
        }

        // Observing LiveData
        viewModel.liveData.observe(this) { value ->
            println("LiveData: $value")
        }
    }
}

@Composable
fun Observables(viewModel: MainViewModel, activity: MainActivity, modifier: Modifier = Modifier) {

    var result by rememberSaveable { mutableStateOf("Show Result") }

    viewModel.liveData.observe(activity) { value ->
        result = value
    }

    Column(modifier = modifier.fillMaxSize()) {
        Button(onClick = {
            viewModel.updateLiveData("Hello LiveData")
        }) {
            Text(text = "Live Data")
        }

        Text(text = result)
    }
}

@Preview(showBackground = true)
@Composable
fun ObservablesPreview() {
    StateFlowTheme {
        val viewModel = MainViewModel()
        val activity = MainActivity()
        Observables(viewModel = viewModel, activity = activity)
    }
}