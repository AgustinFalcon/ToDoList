package com.example.practicandoroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.practicandoroom.data.UserDataBase
import com.example.practicandoroom.data.entities.User
import com.example.practicandoroom.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val practicandoMaster: UserRepository
    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository
    private val practicandoRama: UserRepository
    init {
        val userDao = UserDataBase.getDataBase(application).userDao()
        repository = UserRepository(userDao)
        practicandoMaster = UserRepository(userDao)
        readAllData = repository.readAllData
        practicandoRama = UserRepository(userDao)
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}