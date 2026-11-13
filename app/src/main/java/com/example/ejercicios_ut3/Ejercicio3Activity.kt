package com.example.ejercicios_ut3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Ejercicio3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejercicio3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val destinos = mapOf(
            R.id.btnVerde to Ejercicio3VerdeActivity::class.java,
            R.id.btnTablero to Ejercicio3TableroActivity::class.java,
            R.id.btnMixto to Ejercicio3MixtoActivity::class.java,
        )

        findViewById<Button>(R.id.btnSalir).setOnClickListener {
            finish()
        }
        destinos.forEach { (id, clase) ->
            findViewById<Button>(id).setOnClickListener {
                startActivity(Intent(this, clase))
            }
        }
    }
}