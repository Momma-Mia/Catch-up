package kr.hs.dgsw.mommamia.catchup.root.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kr.hs.dgsw.mommamia.catchup.feature.intro.AddFamilyScreen
import kr.hs.dgsw.mommamia.catchup.feature.intro.IntroScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            IntroScreen()
        }
    }
}
