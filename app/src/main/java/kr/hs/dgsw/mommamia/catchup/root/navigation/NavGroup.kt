package kr.hs.dgsw.mommamia.catchup.root.navigation

import androidx.compose.runtime.Composable

sealed class NavGroup(val name: String) {
    object Sign : NavGroup("sign")
    object AddFamily : NavGroup("add_family")
}