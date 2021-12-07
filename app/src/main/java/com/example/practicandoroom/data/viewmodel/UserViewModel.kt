package com.example.practicandoroom.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.practicandoroom.data.UserDatabase
import com.example.practicandoroom.data.entities.User
import com.example.practicandoroom.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//el application le da el context para el HelperDataBase
class UserViewModel(application: Application): AndroidViewModel(application){

    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao() //Recibe la instancia de la database
        repository = UserRepository(userDao = userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        repository.addUser(user = user)
    }

    fun deleteUser(id: Int){
        CoroutineScope(Dispatchers.IO).launch{
            repository.deleteUser(id = id)
        }
    }

}