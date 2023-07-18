package com.example.firstapp.ui

import androidx.lifecycle.*
import com.example.firstapp.api.ApiState
import com.example.firstapp.db.UserDao
import com.example.firstapp.paging
import com.example.firstapp.repository.ApiRepositorySdk
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor( private val userDao: UserDao,
    private val repository: ApiRepositorySdk, savedStateHandle: SavedStateHandle

) : ViewModel(), paging {



    private val data:MutableLiveData<List<com.example.firstapp.response.UserList.Result>>
        get() {
            TODO()
        }


    private val _userslist =
        MutableStateFlow<List<com.example.firstapp.response.UserList.Result>>(emptyList())

    val userslist: StateFlow<List<com.example.firstapp.response.UserList.Result>>
        get() = _userslist

    val loadingState= MutableStateFlow(false)

    init {
        getUsers()
    }
//    fun getAllRepositoryList(): LiveData<List<com.example.firstapp.response.UserList.Result>> {
//        return repository.getUserList(pageIndex:)
//    }

//userDao.insertRecords(it)
    private fun getUsers() {
        viewModelScope.launch {
            repository.getUserList(page = pageIndex).collect {
                when (it) {
                    is ApiState.Success -> {

                        loadingState.value=false
                        isLoading = false
                        it.data
                        it.data?.results?.let {
                           // userDao.insertRecords(it)
                            pageIndex++
                            val prev = _userslist.value.toMutableList()
                            prev.addAll(it)
                            _userslist.value = prev
                        }?: kotlin.run {
                            if (pageIndex == 1) {
                                _userslist.value = emptyList()
                                resetPaging()
                            }
                        }
                        if (it.data?.info?.pages == pageIndex-1) {
                            isLastPage = true
                        }
                    }
                    is ApiState.Loading -> {
                        isLoading = true
                        loadingState.value=true

                    }
                    is ApiState.Failure -> {

//                        fun getAllRepositoryList(): LiveData<List<com.example.firstapp.response.UserList.Result>> {
//                            return userDao.getUsers()
//                        }
                     //   _userslist.value=repository.userDao.getUsers()


                      //  _userslist.value=

//                        data.value.let {
////                            repository.userDao.getUsers()
////                        }
                        isLoading = false
                        loadingState.value=false
                    }
                }

            }
        }


    }
    fun getAllRepositoryList(): LiveData<List<com.example.firstapp.response.UserList.Result>> {
        return userDao.getUsers()
    }

    override var pageIndex: Int = 1

    override var isLoading: Boolean = false

    override var isLastPage: Boolean = false


    override fun fetchData(arg: Any?) {
        getUsers()
    }


}