package com.example.room_example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.room_example.database.ContactDatabase
import com.example.room_example.databinding.ActivityMainBinding
import com.example.room_example.entity.Contact

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var database: ContactDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = ContactDatabase.getInstance(this)

        binding.btnSave.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val contact = Contact(firstName, lastName, phoneNumber)

            database.contactDao().addContact(contact)

            binding.etPhoneNumber.text.clear()
            binding.etFirstName.text.clear()
            binding.etLastName.text.clear()
        }
        binding.btnContacts.setOnClickListener {
            val intent = Intent(this,ContactActivity::class.java)
            startActivity(intent)
        }
    }
}