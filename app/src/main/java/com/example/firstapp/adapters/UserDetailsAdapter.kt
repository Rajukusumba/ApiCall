package com.example.firstapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstapp.databinding.UsersListLayoutBinding
import com.example.firstapp.response.UserDetails

class UserDetailsAdapter:RecyclerView.Adapter<UserDetailsAdapter.DetailsHolder>() {
  inner  class DetailsHolder(private var binding: UsersListLayoutBinding):RecyclerView.ViewHolder(binding.root) {

      fun bindData(userDetails: UserDetails){
         binding.name.text=userDetails.name
          binding.status.text=userDetails.status
          binding.gender.text=userDetails.gender
          Glide.with(itemView).load(userDetails.image).into(binding.img)
      }

    }
    private val differCallback =object : DiffUtil.ItemCallback<UserDetails>(){
    override fun areItemsTheSame(oldItem: UserDetails, newItem: UserDetails): Boolean {
       return return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserDetails, newItem: UserDetails): Boolean {
       return  oldItem == newItem
    }

}
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsHolder {
        val binding =
            UsersListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DetailsHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bindData(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}