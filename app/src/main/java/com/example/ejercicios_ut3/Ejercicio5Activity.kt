package com.example.ejercicios_ut3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Ejercicio5Activity : AppCompatActivity() {

    private lateinit var tvPantalla: TextView

    private var operando1: Double? = null
    private var operador: String? = null
    private var empezarNumeroNuevo = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejercicio5)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvPantalla = findViewById(R.id.tvPantalla)

        // --- Cifras: todas hacen lo mismo, solo cambia el número ---
        val cifras = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )
        cifras.forEach { id ->
            val boton = findViewById<Button>(id)
            boton.setOnClickListener { pulsarCifra(boton.text.toString()) }
        }

        // --- Operaciones: igual, usamos el texto del botón (+ - * /) ---
        val operaciones = listOf(R.id.btnSumar, R.id.btnRestar, R.id.btnMultiplicar, R.id.btnDividir)
        operaciones.forEach { id ->
            val boton = findViewById<Button>(id)
            boton.setOnClickListener { pulsarOperador(boton.text.toString()) }
        }

        findViewById<Button>(R.id.btnPunto).setOnClickListener { pulsarPunto() }
        findViewById<Button>(R.id.btnIgual).setOnClickListener { pulsarIgual() }
        findViewById<Button>(R.id.btnC).setOnClickListener { reiniciar() }
    }

    private fun pulsarCifra(cifra: String) {
        if (empezarNumeroNuevo || tvPantalla.text == "0") {
            tvPantalla.text = cifra                  // sustituye lo que hubiera
            empezarNumeroNuevo = false
        } else {
            tvPantalla.append(cifra)                 // añade detrás: 1 → 12
        }
    }

    private fun pulsarPunto() {
        if (empezarNumeroNuevo) {
            tvPantalla.text = "0."
            empezarNumeroNuevo = false
        } else if (!tvPantalla.text.contains(".")) {  // solo un punto por número
            tvPantalla.append(".")
        }
    }

    private fun pulsarOperador(op: String) {
        // Si ya había una operación a medias (12 + 5 y pulsas -), la resolvemos primero
        if (operador != null && !empezarNumeroNuevo) {
            pulsarIgual()
        }
        operando1 = tvPantalla.text.toString().toDouble()
        operador = op
        empezarNumeroNuevo = true
    }

    private fun pulsarIgual() {
        val a = operando1 ?: return                   // si no hay nada guardado, no hacemos nada
        val b = tvPantalla.text.toString().toDouble()

        val resultado = when (operador) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> if (b == 0.0) null else a / b
            else -> return
        }

        if (resultado == null) {
            tvPantalla.text = "Error"
        } else {
            tvPantalla.text = formatear(resultado)
        }
        operando1 = null
        operador = null
        empezarNumeroNuevo = true
    }

    private fun reiniciar() {
        tvPantalla.text = "0"
        operando1 = null
        operador = null
        empezarNumeroNuevo = true
    }

    // 17.0 → "17"   |   2.5 → "2.5"
    private fun formatear(n: Double): String =
        if (n % 1 == 0.0) n.toLong().toString() else n.toString()
}