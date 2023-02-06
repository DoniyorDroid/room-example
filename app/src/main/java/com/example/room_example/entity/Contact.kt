package com.example.room_example.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Contact Table
@Entity(tableName = "Contacts")
class Contact(
    @ColumnInfo(name = "first_name") var firstName: String?,
    @ColumnInfo(name = "last_name") var lastName: String?,
    @ColumnInfo(name = "phone_number") var phoneNumber: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    override fun toString(): String {
        return "Contact(firstName=$firstName, lastName=$lastName, phoneNumber=$phoneNumber, id=$id)"
    }

    fun fullName(): String {
        return "$firstName $lastName"
    }
}