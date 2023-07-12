package dgsw.hackathon.catch_up.feature.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dgsw.hackathon.catch_up.R
import dgsw.hackathon.catch_up.databinding.FragmentLoginBinding
import dgsw.hackathon.catch_up.feature.login.adapter.LoginAdapter
import dgsw.hackathon.catch_up.feature.main.MainActivity
import dgsw.hackathon.catch_up.util.CatchUpApplication
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager()

        if (CatchUpApplication.prefs.isLogin()) navigateToMain()
        else {
            binding.startWithKakaoButton.setOnClickListener {
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
                    UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                        login(token, error)
                    }
                } else {
                    UserApiClient.instance.loginWithKakaoAccount(requireContext()) { token, error ->
                        login(token, error)
                    }
                }
            }

        }
    }

    private fun initViewPager() = with(binding.viewpager) {
        offscreenPageLimit = 3

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx

        setPageTransformer { page, position ->
            page.translationX = -pageTranslationX * position
        }

        var bannerPosition = 0

        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                lifecycleScope.launch {
                    delay(2500)
                    setCurrentItem(++bannerPosition, true)
                }
            }
        })

        binding.viewpager.adapter = LoginAdapter(
            listOf(
                R.drawable.catchupdad,
                R.drawable.catchupdau,
                R.drawable.catchupgrandpa,
                R.drawable.catchupmom,
                R.drawable.catchupgranma,
                R.drawable.catchupson
            )
        )
    }

    private fun login(token: OAuthToken?, error: Throwable?) {
        if (error != null) {
            Log.e("loginFragment", "${error.message}")
            Log.d("ㅆ끼발", "1")

            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                Log.d("ㅆ끼발", "2")
                return
            }
            UserApiClient.instance.loginWithKakaoAccount(requireContext()) { token, error ->
                if (error != null) {
                    Log.d("ㅆ끼발", "3")
                    Log.e("loginFragment", "${error.stackTrace}")
                } else if (token != null) {
                    Log.d("ㅆ끼발", "4")
                    CatchUpApplication.prefs.apply {
                        setString("accessToken", token.accessToken)
                        setString("refreshToken", token.refreshToken)
                        autoLogin()
                    }
                    findNavController().navigate(R.id.action_loginFragment_to_addFamilyFragment)
                }
            }
        } else if (token != null) {
            Log.d("ㅆ끼발", "5")
            CatchUpApplication.prefs.apply {
                setString("accessToken", token.accessToken)
                setString("refreshToken", token.refreshToken)
                autoLogin()
            }
            navigateToMain()
        }
    }

    private fun navigateToMain() {
        startActivity(Intent(requireContext(), MainActivity::class.java))
    }
}

