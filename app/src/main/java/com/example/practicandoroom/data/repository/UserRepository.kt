package com.example.practicandoroom.data.repository

import androidx.lifecycle.LiveData
import com.example.practicandoroom.data.daos.UserDao
import com.example.practicandoroom.data.entities.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun deleteUser(id: Int){
        userDao.deleteUser(id = id)
    }


}