package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }



        val registerText = findViewById<TextView>(R.id.checkedTextView)
        registerText.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
        // Referencias a las vistas
        val etUsername = findViewById<EditText>(R.id.editTextText)  // Campo de nombre
        val etPassword = findViewById<EditText>(R.id.editTextTextPassword)  // Campo de contraseña
        val btnLogin = findViewById<Button>(R.id.button)  // Botón de login

        btnLogin.setOnClickListener {
            val inputName = etUsername.text.toString()
            val inputPassword = etPassword.text.toString()

            // Obtener datos guardados
            val prefs = getSharedPreferences("user_data", MODE_PRIVATE)
            val savedName = prefs.getString("name", null)
            val savedPassword = prefs.getString("password", null)

            // Validación combinada (sin especificar qué está mal)
            if (savedName == inputName && savedPassword == inputPassword) {
                Toast.makeText(this, "¡Bienvenido, $inputName!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity2::class.java).apply {
                    putExtra("USERNAME", inputName) // Envía el nombre a MainActivity2
                }
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Nombre o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                // Limpiar campos opcional:
                etPassword.text.clear()
            }
        }

    }
}