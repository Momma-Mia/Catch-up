package dgsw.hackathon.catch_up.util

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class CatchUpApplication : Application() {
    companion object {
        lateinit var prefs: PreferenceManager
    }

    override fun onCreate() {
        super.onCreate()
        prefs = PreferenceManager(applicationContext)
        KakaoSdk.init(this, "50af166391affe322852cf48a3cf5e38")
    }
}