package com.example.ejercicios_ut3

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Ejercicio2Activity : AppCompatActivity() {

    // Handler asociado al hilo principal (el de la interfaz)
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejercicio2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvEstado = findViewById<TextView>(R.id.tvEstado)
        val btnIr = findViewById<Button>(R.id.btnIr)

        btnIr.setOnClickListener {
            // 1) Intent normal hacia la segunda activity
            val intent = Intent(this, Ejercicio2DestinoActivity::class.java)

            // 2) Lo envolvemos en un PendingIntent ("intent para lanzar más tarde")
            val pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )

            // 3) El Handler lo lanza pasados 10 000 ms
            handler.postDelayed({
                pendingIntent.send()
                btnIr.isEnabled = true
                tvEstado.text = "Pulsa el botón para ir a la segunda pantalla"
            }, 10_000)

            btnIr.isEnabled = false   // evita pulsarlo varias veces
            tvEstado.text = "Navegando en 10 segundos..."
            Toast.makeText(this, "Espera 10 segundos", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnSalir).setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Si salimos antes de los 10 s, cancelamos el lanzamiento pendiente
        handler.removeCallbacksAndMessages(null)
    }
}