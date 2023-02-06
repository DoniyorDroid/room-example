package com.example.room_example.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room_example.R
import com.example.room_example.databinding.ItemContactBinding
import com.example.room_example.entity.Contact

class ContactAdapter(var list: List<Contact>, var listener: ClickListener) :
    RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(contact: Contact, position: Int) {
            val binding = ItemContactBinding.bind(itemView)

            binding.tvName.text = contact.fullName()
            binding.tvNumber.text = contact.phoneNumber

            binding.ivDelete.setOnClickListener {
                listener.clickDelete(contact, position)
            }
            binding.ivEdit.setOnClickListener {
                listener.clickEdit(contact, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size


    interface ClickListener {
        fun clickDelete(contact: Contact, position: Int)
        fun clickEdit(contact: Contact, position: Int)
    }
}