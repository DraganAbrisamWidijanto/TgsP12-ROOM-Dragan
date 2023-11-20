package com.example.tgsp12_crud_dragan

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.tgsp12_crud_dragan.adapter.UserAdapter
import com.example.tgsp12_crud_dragan.data.AppDatabase
import com.example.tgsp12_crud_dragan.data.entity.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

// Aktivitas utama yang menampilkan daftar pengguna dan menyediakan aksi CRUD
class MainActivity : AppCompatActivity() {
    // Mendeklarasikan elemen UI
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var list= mutableListOf<User>()
    private lateinit var adapter: UserAdapter
    private lateinit var database: AppDatabase

    // Metode yang dipanggil saat aktivitas dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menetapkan tata letak untuk aktivitas dari file XML
        setContentView(R.layout.activity_main)

        // Menghubungkan elemen UI dengan objeknya
        recyclerView=findViewById(R.id.recycler_View)
        fab=findViewById(R.id.fab)

        // Mendapatkan instance database menggunakan AppDatabase
        database= AppDatabase.getInstance(applicationContext)

        // Inisialisasi adapter dan menetapkan fungsi dialog klik untuk adapter
        adapter = UserAdapter(list)
        adapter.setDialog(object : UserAdapter.Dialog{
            override fun onClick(position: Int) {
                // Membuat dialog aksi ketika item di klik
                val dialog= AlertDialog.Builder(this@MainActivity)
                dialog.setTitle(list[position].fullName)
                dialog.setItems(R.array.items_option,DialogInterface.OnClickListener{
                    dialog, which ->
                    if(which==0){
                        // Aksi mengubah data
                        val intent=Intent(this@MainActivity,EditorActivity::class.java)
                        intent.putExtra("id",list[position].uid)
                        startActivity(intent)
                    } else if(which==1){
                        //aksi menghapus data
                        database.userDao().delete(list[position])
                        getData()
                    } else{
                        //batalkan aksi
                        dialog.dismiss()
                    }
                })
                //menampilkan dialog
                val dialogView = dialog.create()
                dialogView.show()
            }
        })

        // Mengatur adapter, layout manager, dan dekorasi item untuk RecyclerView
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        // Menetapkan fungsi klik untuk tombol fab (FloatingActionButton)
        fab.setOnClickListener {
            startActivity(Intent(this, EditorActivity::class.java))
        }
    }

    // Metode yang dipanggil saat aktivitas dilanjutkan (di-resume)
    override fun onResume() {
        super.onResume()
        // Mendapatkan data terbaru setiap kali aktivitas di-resume
        getData()
    }

    // Fungsi untuk mendapatkan data pengguna dari database dan memperbarui tampilan
    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        // Menghapus data lama dan menambahkan data baru dari database
        list.clear()
        list.addAll(database.userDao().getAll())
        // Memberi tahu adapter bahwa dataset telah berubah
        adapter.notifyDataSetChanged()
    }
}