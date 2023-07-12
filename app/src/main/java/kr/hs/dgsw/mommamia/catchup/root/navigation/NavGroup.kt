package kr.hs.dgsw.mommamia.catchup.root.navigation

import androidx.compose.runtime.Composable

sealed class NavGroup(val name: String) {
    object Home : NavGroup("home")
    object Map : NavGroup("map")
    object User : NavGroup("user")
}