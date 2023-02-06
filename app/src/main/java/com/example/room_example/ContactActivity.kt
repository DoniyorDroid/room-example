package com.example.room_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.room_example.adapter.ContactAdapter
import com.example.room_example.database.ContactDatabase
import com.example.room_example.databinding.ActivityContactBinding
import com.example.room_example.databinding.DialogContactBinding
import com.example.room_example.entity.Contact

class ContactActivity : AppCompatActivity() {
    lateinit var binding: ActivityContactBinding
    lateinit var database: ContactDatabase
    lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = ContactDatabase.getInstance(this)

        val allContacts = database.contactDao().getAllContacts() as ArrayList

        adapter = ContactAdapter(allContacts, object : ContactAdapter.ClickListener {
            override fun clickDelete(contact: Contact, position: Int) {
                database.contactDao().deleteContact(contact)
                allContacts.remove(contact)
                adapter.notifyItemRemoved(position)
                adapter.notifyItemRangeChanged(position, allContacts.size)
            }

            override fun clickEdit(contact: Contact, position: Int) {
                val alert = AlertDialog.Builder(this@ContactActivity).create()
                val dialogView = LayoutInflater.from(this@ContactActivity)
                    .inflate(R.layout.dialog_contact, binding.root, false)

                alert.setView(dialogView)
                alert.show()
                val bind = DialogContactBinding.bind(dialogView)
                bind.etFirstName.setText(contact.firstName)
                bind.etLastName.setText(contact.lastName)
                bind.etPhoneNumber.setText(contact.phoneNumber)

                bind.btnUpdate.setOnClickListener {
                    val firstName = bind.etFirstName.text.toString()
                    val lastName = bind.etLastName.text.toString()
                    val phoneNumber = bind.etPhoneNumber.text.toString()

                    contact.firstName = firstName
                    contact.lastName = lastName
                    contact.phoneNumber = phoneNumber

                    database.contactDao().updateContact(contact)
                    allContacts[position] = contact
                    adapter.notifyItemChanged(position)
                    alert.dismiss()
                }
            }

        })
        binding.rv.adapter = adapter
    }
}