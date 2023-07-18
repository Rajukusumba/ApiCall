package com.example.firstapp.repository

import com.example.firstapp.api.ApiServices
import com.example.firstapp.api.ApiState
import com.example.firstapp.db.UserDao
import com.example.firstapp.response.UserDetails
import com.example.firstapp.response.UserList.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

class ApiRepositorySdk(var apiServices: ApiServices,  val userDao: UserDao) {

     suspend fun getUserList (page:Int): Flow<ApiState<UserResponse>> =
        flow {
            emit(ApiState.Loading())
            kotlin.runCatching {
                apiServices.getUserList(page)
            }.onSuccess {
                it.results?.let { it1 -> userDao.insertRecords(it1) }
                emit(ApiState.Success(it))
            }.onFailure {
                userDao.getUsers()
                emit(ApiState.Failure(it.message))

            }
        }

    suspend fun getUserDetails (id:Int): Flow<ApiState<UserDetails>> =
        flow {
            emit(ApiState.Loading())
            kotlin.runCatching {
                apiServices.getUserDetails(id)
            }.onSuccess {
                emit(ApiState.Success(it))
            }.onFailure {
                emit(ApiState.Failure(it.message))
            }
        }
}