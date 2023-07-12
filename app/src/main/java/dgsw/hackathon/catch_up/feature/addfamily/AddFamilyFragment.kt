package dgsw.hackathon.catch_up.feature.addfamily

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kakao.sdk.friend.client.PickerClient
import com.kakao.sdk.friend.model.OpenPickerFriendRequestParams
import com.kakao.sdk.friend.model.PickerOrientation
import com.kakao.sdk.friend.model.ViewAppearance
import com.kakao.sdk.talk.TalkApiClient
import dgsw.hackathon.catch_up.databinding.FragmentAddFamilyBinding
import dgsw.hackathon.catch_up.model.User


class AddFamilyFragment : Fragment() {
    private val binding by lazy { FragmentAddFamilyBinding.inflate(layoutInflater) }
    private val viewModel: AddFamilyViewmodel by activityViewModels()
    private lateinit var userList: ArrayList<User>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()



    }
    private fun initRecycler(){
        userList.add(User("","","추가"))
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = AddFamilyAdapter(userList)
        }
    }

    private fun showPicker(){
        // 파라미터 설정
        val openPickerFriendRequestParams = OpenPickerFriendRequestParams(
            title = "풀 스크린 싱글 친구 피커", //default "친구 선택"
            viewAppearance = ViewAppearance.AUTO, //default ViewAppearance.AUTO
            orientation = PickerOrientation.AUTO, //default PickerOrientation.AUTO
            enableSearch = true, //default true
            enableIndex = true, //default true
            showMyProfile = true, //default true
            showFavorite = true //default true
        )

// 피커 호출
        PickerClient.instance.selectFriend(
            context = requireContext(),
            params = openPickerFriendRequestParams
        ) { selectedUsers, error ->
            if (error != null) {
                Log.e("euya", "친구 선택 실패", error)
            } else {
                Log.d("euya", "친구 선택 성공 $selectedUsers")
            }
        }
    }

}