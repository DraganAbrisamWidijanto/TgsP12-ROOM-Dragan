package com.example.tgsp12_crud_dragan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tgsp12_crud_dragan.data.AppDatabase
import com.example.tgsp12_crud_dragan.data.entity.User

class EditorActivity : AppCompatActivity() {
    private lateinit var fullName:EditText
    private lateinit var email:EditText
    private lateinit var phone:EditText
    private lateinit var savebtn:Button
    private lateinit var database: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        fullName= findViewById(R.id.fullnametxt)
        email= findViewById(R.id.emailtxt)
        phone= findViewById(R.id.phonetxt)
        savebtn= findViewById(R.id.savebtn)

        database= AppDatabase.getInstance(applicationContext)

        val intent=intent.extras
        if (intent!=null){
            val id = intent.getInt("id",0)
            val user=database.userDao().findById(id)

            fullName.setText(user.fullName)
            email.setText(user.email)
            phone.setText(user.phoneNumber)
        }

        savebtn.setOnClickListener {
            if (fullName.text.isNotEmpty() && email.text.isNotEmpty() && phone.text.isNotEmpty()) {
                //edit data
                if(intent != null) {
                    database.userDao().update(
                        User(
                            intent.getInt("id", 0),
                            fullName.text.toString(),
                            email.text.toString(),
                            phone.text.toString()
                        )
                    )
                } else{
                    //tambah data
                    database.userDao().insertAll(
                        User(
                            null,
                            fullName.text.toString(),
                            email.text.toString(),
                            phone.text.toString()
                        )
                    )
                }
                finish()
            } else {
                Toast.makeText(applicationContext, "Datanya tolong diisi bro", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}