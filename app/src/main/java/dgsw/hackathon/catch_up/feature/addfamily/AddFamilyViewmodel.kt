package dgsw.hackathon.catch_up.feature.addfamily

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kakao.sdk.talk.TalkApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AddFamilyViewmodel : ViewModel() {


    fun takeProfile() = CoroutineScope(Dispatchers.IO).launch {
        TalkApiClient.instance.profile { profile, error ->
            if (error != null) {
                Log.e("euya", "카카오톡 프로필 가져오기 실패",error)
            }
            else if (profile != null) {
                Log.i("euya", "카카오톡 프로필 가져오기 성공" +
                        "\n닉네임: ${profile.nickname}" +
                        "\n프로필사진: ${profile.thumbnailUrl}")
            }
        }
    }
}