package com.example.practicandoroom.data

import android.content.Context
import kotlin.jvm.Volatile
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practicandoroom.data.daos.UserDao
import com.example.practicandoroom.data.entities.User


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase(){

    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null //Singelton

        //si no existe una instancia crea una y en otro hilo le da el valor de la misma
        fun getDatabase(context: Context): UserDatabase{
            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
                //si tiene una instancia la retorno
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "helper_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }


}