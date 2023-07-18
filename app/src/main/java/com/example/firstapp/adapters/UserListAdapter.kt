package com.example.firstapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstapp.R
import com.example.firstapp.databinding.UsersListLayoutBinding
import com.example.firstapp.ui.UsersListFragmentDirections

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    inner class UserViewHolder(private val binding: UsersListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("ResourceAsColor")
        fun bindData(article: com.example.firstapp.response.UserList.Result) {
            binding.name.text = article.name
            binding.status.text = article.status
            binding.gender.text = article.gender

            Glide.with(itemView)
                .load(article.image)
                .into(binding.img)

            if (adapterPosition.mod(2).equals(0)) {
                binding.linear.setBackgroundColor(R.color.purple_500)
            }

            binding.linear.setOnClickListener {
                val action =
                    UsersListFragmentDirections.actionUsersListFragmentToUserDetailsFragment(
                        userid = adapterPosition
                    )
                binding.root.findNavController().navigate(action)
            }
        }
    }

    private val differCallback =
        object : DiffUtil.ItemCallback<com.example.firstapp.response.UserList.Result>() {
            override fun areItemsTheSame(
                oldItem: com.example.firstapp.response.UserList.Result,
                newItem: com.example.firstapp.response.UserList.Result
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: com.example.firstapp.response.UserList.Result,
                newItem: com.example.firstapp.response.UserList.Result
            ): Boolean {
                return oldItem == newItem
            }

        }


    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            UsersListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return UserViewHolder(binding)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bindData(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}