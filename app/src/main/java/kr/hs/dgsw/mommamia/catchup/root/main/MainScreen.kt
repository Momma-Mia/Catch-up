package kr.hs.dgsw.mommamia.catchup.root.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kr.hs.dgsw.mommamia.catchup.root.navigation.NavigationHost

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavigationHost(navController = navController)
}