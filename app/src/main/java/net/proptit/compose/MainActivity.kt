package net.proptit.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import net.proptit.compose.screen.DetailScreen
import net.proptit.compose.screen.ListScreen
import net.proptit.compose.ui.theme.ProptitcomposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            // set view
            ProptitcomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navigator = rememberNavController()
                    val mainVimel: MainVimel = viewModel()

                    NavHost(navController = navigator, startDestination = Screen.ListScreen.route) {
                        navigation(startDestination = Screen.ListScreen.route, route = "todo") {
                            composable(Screen.ListScreen.route) {
                                val state by mainVimel.state.collectAsState()
                                ListScreen(
                                    todos = state.todos,
                                    onEvent = mainVimel::onEvent,
                                    onNavigateToDetail = { navigator.navigate("detail/$it") }
                                )
                            }

                            composable(Screen.DetailScreen.route) {
                                DetailScreen(it.arguments?.getString("id")!!.toInt())
                            }

                            composable("add") {

                            }
                        }

                        navigation(startDestination = "login", route = "user") {
                            composable("login") {
                            }

                            composable("signup") {
                                DetailScreen(it.arguments?.getString("id")!!.toInt())
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {

}