package com.example.myapplication.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
import com.example.myapplication.R.id.email_btn
import com.example.myapplication.R.id.loginbtn
import com.example.myapplication.R.id.pass_word

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.loginactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonNext: Button = findViewById(loginbtn)
        val editTextEmail: EditText = findViewById(email_btn)
        val editTextPassword: EditText = findViewById(pass_word)


        buttonNext.setOnClickListener {
            // Get the email and password values from the EditText fields
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            // Check if both email and password are filled
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // If both are filled, move to the Dashboard
                val intent = Intent(this,Dashborad::class.java)
                startActivity(intent)  // Start the next activity
            } else {
                // If either email or password is empty, show a Toast message
                Toast.makeText(this, "Invalid password and userID", Toast.LENGTH_SHORT)
                    .show()
            }
        }}}
