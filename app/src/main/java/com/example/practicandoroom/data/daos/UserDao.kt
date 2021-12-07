package com.example.practicandoroom.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.practicandoroom.data.entities.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC;")
    fun readAllData(): LiveData<List<User>>


    @Query("DELETE FROM user_table WHERE id=:id;")
    suspend fun deleteUser(id: Int)
}