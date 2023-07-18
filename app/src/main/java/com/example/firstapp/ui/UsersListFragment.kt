package com.example.firstapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.PagingScrollListener
import com.example.firstapp.R
import com.example.firstapp.adapters.UserListAdapter
import com.example.firstapp.databinding.FragmentUsersListBinding
import com.example.firstapp.databinding.UsersListLayoutBinding
import com.example.firstapp.db.UserDao
import com.example.firstapp.response.UserList.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UsersListFragment : Fragment() {

    lateinit var userAdapter: UserListAdapter

    private val viewModel by viewModels<UsersListViewModel>()

    private lateinit var binding: FragmentUsersListBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersListBinding.inflate(inflater, container, false)
        userAdapter = UserListAdapter()
        binding.rcv.setLayoutManager(LinearLayoutManager(context))
        binding.rcv.adapter = userAdapter
        lifecycleScope.launch {
//            viewModel.userslist.collect {
//                userAdapter.differ.submitList(it)
//            }
            viewModel.getAllRepositoryList().observe(viewLifecycleOwner, Observer<List<Result>> {
                userAdapter.differ.submitList(it)
        })
        }
//        viewModel.getAllRepositoryList().observe(viewLifecycleOwner, Observer<List<Result>> {
//            userAdapter.differ.submitList(it)


       // userAdapter.differ.submitList(UserDao.getUsers())

        lifecycleScope.launch {
            viewModel.loadingState.collect{
                binding.probar.isVisible=it
            }
        }


        binding.rcv.addOnScrollListener(object : PagingScrollListener(){
            override fun isLastPage(): Boolean {
                return viewModel.isLastPage
            }

            override fun isLoading(): Boolean {
               return viewModel.isLastPage
            }

            override fun loadMoreItems() {
               viewModel.fetchData()
            }

            override fun onScroll(recyclerView: RecyclerView, dx: Int, dy: Int) {
              //  TODO("Not yet implemented")
            }

            override fun onScrollStateChange(recyclerView: RecyclerView, newState: Int) {
             //   TODO("Not yet implemented")
            }

        })
//        binding.btn.setOnClickListener {
//            viewModel.fetchData()
//        }
        return binding.root

    }


}