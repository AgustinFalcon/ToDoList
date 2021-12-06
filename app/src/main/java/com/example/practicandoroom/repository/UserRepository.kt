package com.example.practicandoroom.repository

import androidx.lifecycle.LiveData
import com.example.practicandoroom.data.daos.UserDao
import com.example.practicandoroom.data.entities.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}