package com.example.tgsp12_crud_dragan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tgsp12_crud_dragan.R
import com.example.tgsp12_crud_dragan.data.entity.User

// Adapter untuk RecyclerView yang menangani tampilan item pengguna
class UserAdapter(var list: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    // Dialog yang akan digunakan untuk menangani klik pada item
    private lateinit var dialog: Dialog

    // Fungsi untuk mengatur dialog
    fun setDialog(dialog: Dialog) {
        this.dialog = dialog
    }

    // Antarmuka untuk menangani klik pada item
    interface Dialog {
        fun onClick(position: Int)
    }

    // Kelas inner ViewHolder untuk menangani tampilan item
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var fullName: TextView
        var email: TextView
        var phone: TextView

        // Konstruktor inisialisasi elemen-elemen tampilan dan menetapkan fungsi klik
        init {
            fullName = view.findViewById(R.id.full_name)
            email = view.findViewById(R.id.email)
            phone = view.findViewById(R.id.phone)
            view.setOnClickListener {
                dialog.onClick(layoutPosition)
            }
        }
    }

    // Membuat ViewHolder baru saat RecyclerView memerlukannya
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_usercard, parent, false)
        return ViewHolder(view)
    }

    // Mendapatkan jumlah item dalam daftar
    override fun getItemCount(): Int {
        return list.size
    }

    // Menetapkan data untuk tampilan item pada posisi tertentu
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fullName.text = list[position].fullName
        holder.email.text = list[position].email
        holder.phone.text = list[position].phoneNumber
    }
}
