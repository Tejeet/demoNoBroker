package com.tejeet.guessmyageroommvvm.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserPostsEntity::class], version = 1)
abstract class UserDataBase : RoomDatabase(){

    abstract fun getUserDataDao(): UserDataDao

    companion object {

        var INSTANCE: UserDataBase? = null

        fun getDataBase(context: Context): UserDataBase {

            if (INSTANCE == null) {

                val builder: Builder<UserDataBase> = Room.databaseBuilder(
                    context,
                    UserDataBase::class.java,
                    "USERS_DB"
                )

                builder.fallbackToDestructiveMigration()
                INSTANCE = builder.build()

                return INSTANCE!!

            } else {
                return INSTANCE!!
            }

        }

    }
}