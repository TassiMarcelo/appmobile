package com.example.myapplication

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // 1. Obtener referencias a las vistas
        val etName = findViewById<EditText>(R.id.editTextText2)
        val etEmail = findViewById<EditText>(R.id.editTextText3)
        val etPassword = findViewById<EditText>(R.id.editTextTextPassword2)
        val etRepeatPassword = findViewById<EditText>(R.id.editTextTextPassword3)
        val btnRegister = findViewById<Button>(R.id.button2)

        btnRegister.setOnClickListener {
            // 4. Verificar que las contraseñas coincidan
            if (etPassword.text.toString() != etRepeatPassword.text.toString()) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 5. Guardar datos en SharedPreferences (almacenamiento local)
            val prefs: SharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
            prefs.edit().apply {
                putString("name", etName.text.toString())
                putString("email", etEmail.text.toString())
                putString("password", etPassword.text.toString()) // ¡En producción deberías encriptar esto!
                apply()
            }
            // estos datos se van a guardar por sheredpreferences en
            ///data/data/com.example.myapplication/shared_prefs/user_data.xml
            //se pueden encontrar por el divice explorer una ventana de busqueda

            // 6. Mostrar confirmación y finalizar
            Toast.makeText(this, "Registro exitoso!", Toast.LENGTH_SHORT).show()
            finish() // Cierra esta actividad y vuelve al login


        }

    }
}