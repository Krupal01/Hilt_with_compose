package com.example.hilt_di.module

import com.example.hilt_di.module.qualifier.ItemService
import com.example.hilt_di.module.qualifier.UserService
import com.example.hilt_di.network.ItemNetworkServiceDummy
import com.example.hilt_di.network.NetworkService
import com.example.hilt_di.network.UserNetworkServiceDummy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @ItemService
    @Singleton
    @Provides
    fun getItemService(): NetworkService{
        return ItemNetworkServiceDummy()
    }


    @UserService
    @Singleton
    @Provides
    fun getUserService():NetworkService{
        return UserNetworkServiceDummy()
    }

}