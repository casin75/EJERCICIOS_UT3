package com.example.ejercicios_ut3

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Ejercicio1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejercicio1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etUrl = findViewById<EditText>(R.id.etUrl)

        findViewById<Button>(R.id.Salir).setOnClickListener {
            finish()   // cierra esta activity y vuelve al menú
        }

        findViewById<Button>(R.id.btnAbrir).setOnClickListener {
            var url = etUrl.text.toString().trim()

            // 1) Vacía o no válida → Toast
            if (url.isEmpty() || !Patterns.WEB_URL.matcher(url).matches()) {
                Toast.makeText(this, "Introduce una URL válida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2) Si no lleva protocolo, se lo añadimos
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://$url"
            }

            // 3) Intent implícito para abrirla en el navegador
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "No hay ninguna app para abrir la URL", Toast.LENGTH_SHORT).show()
            }
        }
    }
}