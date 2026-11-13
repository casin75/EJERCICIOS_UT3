package com.example.ejercicios_ut3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

        val destinos = mapOf(
            R.id.btnEj1 to Ejercicio1Activity::class.java,
            R.id.btnEj2 to Ejercicio2Activity::class.java,
            R.id.btnEj3 to Ejercicio3Activity::class.java,
            R.id.btnEj4 to Ejercicio4Activity::class.java,
            R.id.btnEj5 to Ejercicio5Activity::class.java,
            R.id.btnEj6 to Ejercicio6Activity::class.java
        )
        destinos.forEach { (id, clase) ->
            findViewById<Button>(id).setOnClickListener {
                startActivity(Intent(this, clase))
            }
        }
    }
}