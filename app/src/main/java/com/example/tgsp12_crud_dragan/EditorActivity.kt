package com.example.tgsp12_crud_dragan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tgsp12_crud_dragan.data.AppDatabase
import com.example.tgsp12_crud_dragan.data.entity.User

// Aktivitas untuk menangani editor (tambah/edit) data pengguna
class EditorActivity : AppCompatActivity() {

    // Mendeklarasikan elemen UI
    private lateinit var fullName: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var savebtn: Button
    private lateinit var database: AppDatabase

    // Metode yang dipanggil saat aktivitas dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menetapkan tata letak untuk aktivitas dari file XML
        setContentView(R.layout.activity_editor)

        // Menghubungkan elemen UI dengan objeknya
        fullName = findViewById(R.id.fullnametxt)
        email = findViewById(R.id.emailtxt)
        phone = findViewById(R.id.phonetxt)
        savebtn = findViewById(R.id.savebtn)

        // Mendapatkan instance database menggunakan AppDatabase
        database = AppDatabase.getInstance(applicationContext)

        // Mengekstrak data intent jika ada
        val intent = intent.extras
        if (intent != null) {
            // Jika ada intent, ini adalah mode pengeditan
            val id = intent.getInt("id", 0)
            // Mendapatkan data pengguna berdasarkan ID
            val user = database.userDao().findById(id)

            // Mengatur nilai EditText dengan data pengguna yang ada
            fullName.setText(user.fullName)
            email.setText(user.email)
            phone.setText(user.phoneNumber)
        }

        // Menetapkan fungsi klik untuk tombol simpan
        savebtn.setOnClickListener {
            // Memeriksa apakah semua kolom data telah diisi
            if (fullName.text.isNotEmpty() && email.text.isNotEmpty() && phone.text.isNotEmpty()) {
                // Jika intent tidak null, ini adalah mode pengeditan
                if (intent != null) {
                    // Memperbarui data pengguna di dalam database
                    database.userDao().update(
                        User(
                            intent.getInt("id", 0),
                            fullName.text.toString(),
                            email.text.toString(),
                            phone.text.toString()
                        )
                    )
                } else {
                    // Jika intent null, ini adalah mode penambahan data baru
                    // Menambahkan data pengguna baru ke dalam database
                    database.userDao().insertAll(
                        User(
                            null,
                            fullName.text.toString(),
                            email.text.toString(),
                            phone.text.toString()
                        )
                    )
                }
                // Menutup aktivitas setelah menyimpan data
                finish()
            } else {
                // Menampilkan pesan jika ada kolom data yang belum diisi
                Toast.makeText(applicationContext, "Data harus diisi", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
