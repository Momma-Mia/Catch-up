package dgsw.hackathon.catch_up.feature.addfamily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dgsw.hackathon.catch_up.R
import dgsw.hackathon.catch_up.databinding.AddFamilyItemBinding
import dgsw.hackathon.catch_up.model.User

class AddFamilyAdapter(val items: ArrayList<User>): RecyclerView.Adapter<AddFamilyAdapter.ViewHolder>(){
    interface OnItemClickListener{
        fun OnItemClick(url:String)
    }

    var itemClickListener:OnItemClickListener?=null

    inner class ViewHolder(val binding: AddFamilyItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(user: User){
            with(binding){
                nicknameTv.text = user.nickname
                Glide.with(root).load(user.profileUrl).into(profileIv)
                profileIv.clipToOutline = true
            }
            if (user.role == "추가") {
                binding.profileIv.setImageResource(R.drawable.catchupplus)
                binding.profileIv.setOnClickListener {

                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AddFamilyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}