package com.example.practicandoroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practicandoroom.data.daos.UserDao
import com.example.practicandoroom.data.entities.User


@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
) //indica las clases que va a usar como base de datos

abstract class UserDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        //se usa una sola instancia de la base de datos y si contiene null crea una
        @Volatile
        private var INSTANCE: UserDataBase? = null

        fun getDataBase(context: Context): UserDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            //trabaja con la sobre carga de hilos
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
            //end synchronidez
        }
        //end fun
    }


}