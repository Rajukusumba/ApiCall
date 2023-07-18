package com.example.firstapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.firstapp.R
import com.example.firstapp.adapters.UserDetailsAdapter
import com.example.firstapp.adapters.UserListAdapter
import com.example.firstapp.databinding.FragmentUserDetailsBinding
import com.example.firstapp.databinding.FragmentUsersListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    lateinit var userDetailAdapte: UserDetailsAdapter

    private val viewModel by viewModels<UsersDetailsViewModel>()

    private lateinit var binding: FragmentUserDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        userDetailAdapte = UserDetailsAdapter()
        lifecycleScope.launch {
            viewModel.userDetails.collect {
            binding.apply {
                name1.text=it?.name
                gender2.text=it?.gender
                status2.text=it?.status
                Glide.with(this@UserDetailsFragment).load(it?.image).into(img2)
            }
            }
        }
        return binding.root
        //return inflater.inflate(R.layout.fragment_user_details, container, false)
    }


}