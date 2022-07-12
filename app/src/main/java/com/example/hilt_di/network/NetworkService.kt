package com.example.hilt_di.network

interface NetworkService {

    fun getData() : List<String>
}

class ItemNetworkServiceDummy() : NetworkService{

    override fun getData(): List<String> {
        val list = arrayListOf<String>()
        for (i in 0..10){
            list.add("item $i")
        }
        return list
    }

}

class UserNetworkServiceDummy() : NetworkService{

    override fun getData(): List<String> {
        val list = arrayListOf<String>()
        for (i in 0..10){
            list.add("User $i")
        }
        return list
    }

}