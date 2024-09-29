package io.optimizedcode.pingo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.core.common.detectVpn
import io.features.home.ui.screens.HomeScreen
import io.features.home.ui.screens.HomeViewModel
import io.optimizedcode.pingo.ui.theme.PingoTheme
import kotlin.system.exitProcess

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PingoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // Detect VPN ///////////////////////////////////////////////////
                    if (detectVpn()){
                        AlertDialog(
                            onDismissRequest = { exitProcess(0) },
                            title = { Text(text = "Vpn Detected") },
                            text = { Text(text = "Please turn off VPN to use Pingo App") },
                            confirmButton = {
                                Button(
                                    onClick = { exitProcess(0) }
                                ) {
                                    Text(
                                        text = "Close App",
                                        color = Color.White
                                    )
                                }
                            }
                        )
                    }

                    val viewModel = hiltViewModel<HomeViewModel>()
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PingoTheme {
        Greeting("Android")
    }
}