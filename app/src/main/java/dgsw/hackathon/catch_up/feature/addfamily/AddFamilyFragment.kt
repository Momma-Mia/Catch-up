package dgsw.hackathon.catch_up.feature.addfamily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
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
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.recycler.layoutManager = gridLayoutManager
        binding.recycler.adapter = AddFamilyAdapter(userList)
    }

}