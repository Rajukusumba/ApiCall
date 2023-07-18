package com.example.firstapp.di

import android.app.Application
import com.example.firstapp.api.ApiServices
import com.example.firstapp.db.UserDao
import com.example.firstapp.db.UsersDataBase
import com.example.firstapp.repository.ApiRepositorySdk
import com.example.firstapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofitInstance(): ApiServices =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)

    @Provides
    @Singleton
    fun provideRepository(apiServices: ApiServices,userDao: UserDao):ApiRepositorySdk=ApiRepositorySdk(apiServices,userDao)

    @Provides
    @Singleton
    fun getUserDtabase(context: Application): UsersDataBase {
        return UsersDataBase.getUserDbInstance(context)
    }

    @Provides
    @Singleton
    fun getAppDao(appDatabase: UsersDataBase): UserDao {
        return appDatabase.getUserDao()
    }

}