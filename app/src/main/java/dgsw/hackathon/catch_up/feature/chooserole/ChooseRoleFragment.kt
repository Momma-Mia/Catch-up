package dgsw.hackathon.catch_up.feature.chooserole

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dgsw.hackathon.catch_up.R
import dgsw.hackathon.catch_up.databinding.FragmentChooseRoleBinding

class ChooseRoleFragment : Fragment() {
    private val binding by lazy { FragmentChooseRoleBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeButtons()
    }

    private fun observeButtons() {
        with(binding) {
            btnDad.setOnClickListener {
                navigateToAddFamily("아빠")
            }
            btnMom.setOnClickListener {
                navigateToAddFamily("엄마")
            }
            btnSon.setOnClickListener {
                navigateToAddFamily("아들")
            }
            btnDaughter.setOnClickListener {
                navigateToAddFamily("딸")
            }
            btnGrandma.setOnClickListener {
                navigateToAddFamily("할머니")
            }
            btnGrandpa.setOnClickListener {
                navigateToAddFamily("할아버지")
            }
        }

    }

    private fun navigateToAddFamily(text: String) {
        findNavController().navigate(ChooseRoleFragmentDirections.actionChooseRoleFragmentToAddFamilyFragment(text))
    }
}