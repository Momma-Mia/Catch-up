package kr.hs.dgsw.mommamia.catchup.util

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class CatchUpApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "50af166391affe322852cf48a3cf5e38")
    }
}