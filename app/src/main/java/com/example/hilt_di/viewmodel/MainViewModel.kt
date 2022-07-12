package com.example.hilt_di.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.hilt_di.module.qualifier.ItemService
import com.example.hilt_di.module.qualifier.UserService
import com.example.hilt_di.network.NetworkService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ItemService private val itemService : NetworkService,
    @UserService private val userService: NetworkService
) : ViewModel() {

    val _items = mutableStateListOf<String>()
    val _users = mutableStateListOf<String>()

    private fun getItems(){
        _items.addAll(itemService.getData())
    }

    private fun getUsers(){
        _users.addAll(userService.getData())
    }

    init {
        getItems()
        getUsers()
    }


}