package dgsw.hackathon.catch_up.feature.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dgsw.hackathon.catch_up.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {

        } else if (token != null) {
            Log.e("loginFragment", "login in with kakao account token : $token")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startWithKakaoButton.setOnClickListener {

            if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
                //카카오톡 로그인
                UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->

                    if (error != null) {
                        // 카카오톡 로그인 실패


                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }
                        UserApiClient.instance.loginWithKakaoAccount(
                            requireContext(),
                            callback = callback
                        )
                    } else if (token != null) {

                        Log.e("loginFragment", "token == $token")
                        // 로그인을 성공
                    }

                }

            } else {
                //카카오계정 로그인

                UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)

            }
        }
    }
}

