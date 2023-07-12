package dgsw.hackathon.catch_up.feature.chooserole

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dgsw.hackathon.catch_up.databinding.FragmentChooseRoleBinding

class ChooseAdapter(val items: ArrayList<Chosen>): RecyclerView.Adapter<ChooseAdapter.ViewHolder>(){


    inner class ViewHolder(val binding: FragmentChooseRoleBinding):RecyclerView.ViewHolder(binding.root){
        init {
            with(binding){
                
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ViewHolder {
        val view = FragmentChooseRoleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return items.size
    }
}