package com.example.room_example.dao

import androidx.room.*
import com.example.room_example.entity.Contact

@Dao
interface ContactDao {

    @Insert
    fun addContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)

    @Update
    fun updateContact(contact: Contact)

    @Query("SELECT * FROM Contacts")
    fun getAllContacts(): List<Contact>
}