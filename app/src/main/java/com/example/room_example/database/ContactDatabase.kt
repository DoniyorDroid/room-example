package com.example.room_example.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room_example.dao.ContactDao
import com.example.room_example.entity.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        private var instance: ContactDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): ContactDatabase {
            if (instance == null)
                instance =
                    Room.databaseBuilder(ctx, ContactDatabase::class.java, "contact_database")
                        .allowMainThreadQueries()
                        .build()

            return instance!!
        }
    }
}