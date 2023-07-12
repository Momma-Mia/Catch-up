package kr.hs.dgsw.mommamia.catchup.feature.intro

import android.content.Context
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kr.hs.dgsw.mommamia.catchup.R
import kr.hs.dgsw.mommamia.catchup.root.navigation.NavigationHost
import kr.hs.dgsw.mommamia.catchup.ui.theme.CatchUpIcon
import kr.hs.dgsw.mommamia.catchup.ui.theme.CatchUpTheme
import kr.hs.dgsw.mommamia.catchup.ui.theme.Primary

@OptIn(ExperimentalPagerApi::class)
@Composable
fun IntroScreen() {
    val context = LocalContext.current
    val pagerState = rememberPagerState()
    val list = listOf(
        R.drawable.ic_daughter,
        R.drawable.ic_sun,
        R.drawable.ic_mother,
        R.drawable.ic_father,
        R.drawable.ic_grandfather,
        R.drawable.ic_grandmother
    )
    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(1500)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    CatchUpTheme {
        Column(Modifier.fillMaxSize()) {
            Spacer(Modifier.height(100.dp))

            CatchUpIcon(
                Modifier.align(Alignment.CenterHorizontally),
                tint = Primary
            )

            Spacer(Modifier.height(50.dp))

            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                count = 3000,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 80.dp)
            ) { page ->
                Image(
                    modifier = Modifier.size(170.dp),
                    painter = painterResource(list[page % 6]),
                    contentDescription = null
                )
            }

            Box(
                Modifier
                    .fillMaxSize()
                    .padding(bottom = 35.dp)) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 24.dp)
                        .align(Alignment.BottomCenter)
                        .clickable { loginWithKakaoTalk(context) },
                    painter = painterResource(R.drawable.kakao_login_large_wide),
                    contentDescription = null
                )
            }
        }
    }
}

private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
    if (token != null) {
        Log.d("SUCCESS", "loginWithKakaoTalk: $token")
    } else if (error != null) {
        Log.e("ERROR", "loginWithKakaoTalk: $error")
    }
}

private fun loginWithKakaoTalk(context: Context) {
    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->  
            if (token != null) {
                Log.d("SUCCESS", "loginWithKakaoTalk: $token")
            } else if (error != null) {
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }
                UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
            }
        }
    } else {
        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
    }
}

@Preview(showBackground = true)
@Composable
fun IntroScreenPreview() {
    IntroScreen()
}