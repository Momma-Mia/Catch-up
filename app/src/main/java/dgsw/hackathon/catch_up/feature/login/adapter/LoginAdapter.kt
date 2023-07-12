package dgsw.hackathon.catch_up.feature.login.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import dgsw.hackathon.catch_up.databinding.ViewpagerItemBinding

class LoginAdapter(private val items: List<Int>) : RecyclerView.Adapter<LoginAdapter.LoginViewHolder>() {

    inner class LoginViewHolder(val binding: ViewpagerItemBinding): ViewHolder(binding.root){
        fun bind(resource: Int){
            Glide.with(binding.root)
                .load(resource)
                .into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): LoginViewHolder {
        val binding = ViewpagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoginViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoginViewHolder, position: Int) {
        holder.bind(items[position % items.size] - 1)
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }
}