package dgsw.hackathon.catch_up.feature.chooserole

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        with(binding){
            btnDad.setOnClickListener {

            }
            btnMom.setOnClickListener {

            }
            btnSon.setOnClickListener {

            }
            btnDaughter.setOnClickListener {

            }
            btnGrandma.setOnClickListener {

            }
            btnGrandpa.setOnClickListener {

            }
        }
    }
}