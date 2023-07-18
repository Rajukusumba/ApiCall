package com.example.firstapp.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.api.ApiState
import com.example.firstapp.repository.ApiRepositorySdk
import com.example.firstapp.response.UserDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class UsersDetailsViewModel @Inject constructor( private val repository: ApiRepositorySdk, savedStateHandle: SavedStateHandle):ViewModel() {

    private val userid: Int? = savedStateHandle["userid"]

    private val _userdetails=MutableStateFlow<UserDetails?>(null)
    val userDetails:StateFlow<UserDetails?>
        get() =_userdetails

    init {
        getuserdetaills()
    }

    private fun getuserdetaills(){
    viewModelScope.launch {
        repository.getUserDetails(userid!!.plus(1)).collect{
            when(it){
                is ApiState.Success->{
                    it.data.let {
                        _userdetails.value=it
                    }

                }
                is ApiState.Failure->{

                }
                is ApiState.Loading->{

                }
            }
        }
    }
}
}