package dgsw.hackathon.catch_up.feature.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dgsw.hackathon.catch_up.R
import dgsw.hackathon.catch_up.databinding.ItemViewBinding
import dgsw.hackathon.catch_up.feature.main.dataclass.ListData

class ListAdapter(private val context:Context): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var datas = mutableListOf<ListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        private val imgProfile: ImageView = itemView.findViewById(R.id.profileImageView)
        private val txtName: TextView = itemView.findViewById(R.id.nameTextView)
        private val txtTitle: TextView = itemView.findViewById(R.id.titleTextView)
        private val imgPhoto: ImageView = itemView.findViewById(R.id.photoImageView)

        fun bind(item: ListData){
            Glide.with(itemView).load(item.profile_img).into(imgProfile)
            txtName.text = item.name
            txtTitle.text = item.title
            Glide.with(itemView).load(item.photo).into(imgPhoto)
        }
    }

}