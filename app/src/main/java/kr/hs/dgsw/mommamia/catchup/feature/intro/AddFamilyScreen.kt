package kr.hs.dgsw.mommamia.catchup.feature.intro

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kakao.sdk.friend.client.PickerClient
import com.kakao.sdk.friend.model.OpenPickerFriendRequestParams
import com.kakao.sdk.friend.model.PickerOrientation
import com.kakao.sdk.friend.model.SelectedUsers
import com.kakao.sdk.friend.model.ViewAppearance
import com.kakao.sdk.talk.TalkApiClient
import kr.hs.dgsw.mommamia.catchup.ui.theme.AddIcon
import kr.hs.dgsw.mommamia.catchup.ui.theme.CatchUpTheme

@Composable
fun AddFamilyScreen() {
    val context = LocalContext.current

    CatchUpTheme {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(50.dp))
            
            Text(
                text = "가족을\n추가해주세요",
                fontSize = 32.sp,
                color = Color.Black,
                fontWeight = FontWeight.Black,
                lineHeight = 38.sp
            )

            Spacer(Modifier.height(20.dp))

            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2)
            ) {
                item {
                    AddIcon(
                        Modifier.clickable {
                            getKakaoProfile(context) {

                            }
                        }
                    )
                }
            }
        }
    }
}

private fun getKakaoProfile(context: Context, action: (SelectedUsers) -> Unit) {
    TalkApiClient.instance.profile { profile, error ->
        if (error != null) {
            Log.e("ERROR", "getKakaoProfile: $error", )
        }
        else if (profile != null) {
            popUpFriendPicker(context, action)
        }
    }
}

private fun popUpFriendPicker(context: Context, action: (SelectedUsers) -> Unit) {
    val openPickerFriendRequestParams = OpenPickerFriendRequestParams(
        title = "팝업 싱글 친구 피커",
        viewAppearance = ViewAppearance.AUTO,
        orientation = PickerOrientation.AUTO,
        enableSearch = true,
        enableIndex = true,
        showMyProfile = true,
        showFavorite = true
    )

    PickerClient.instance.selectFriendPopup(
        context = context,
        params = openPickerFriendRequestParams
    ) { selectedUsers, error ->
        if (error != null) {
            Log.e("ERROR", "친구 선택 실패", error)
        } else {
            action(selectedUsers!!)
        }
    }
}