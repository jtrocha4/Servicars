package com.example.servicars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.servicars.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityDetailBinding
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clienteTxt: TextView = binding.clienteTxt
        val idClienteTxt: TextView = binding.idClienteTxt
        val telefonoClienteTxt: TextView = binding.telfonoClienteTxt
        val correoClienteTxt: TextView = binding.correoClienteTxt
        val autoTxt: TextView = binding.autoTxt
        val matriculaTxt: TextView = binding.matriculaTxt
        val fechaIngresoTxt: TextView = binding.fechaIngresoTxt
        val estadoTxt: TextView = binding.estadoTxt
        val fallaTxt: TextView = binding.fallaAutoTxt


        val bundle: Bundle? = intent.extras


        val cliente = bundle!!.getString("cliente")
        val idCliente = bundle!!.getInt("idCliente")
        val telefonoCliente = bundle!!.getInt("telefonoCliente")
        val correoCliente = bundle!!.getString("correoCliente")
        val marcaAuto = bundle!!.getString("marcaAuto")
        val modeloAuto = bundle!!.getString("modeloAuto")
        val anioAuto = bundle!!.getInt("anioAuto")
        val matricula = bundle!!.getString("matriculaAuto")
        val fechaIngreso = bundle!!.getString("fechaIngresoAuto")
        val estado = bundle!!.getString("estadoAuto")
        val falla = bundle!!.getString("fallaAuto")

        clienteTxt.text = cliente
        idClienteTxt.text = idCliente.toString()
        telefonoClienteTxt.text = telefonoCliente.toString()
        correoClienteTxt.text = correoCliente
        autoTxt.text = "${marcaAuto} ${modeloAuto} ${anioAuto.toString()}"
        matriculaTxt.text = matricula
        fechaIngresoTxt.text = fechaIngreso
        estadoTxt.text = estado
        fallaTxt.text = falla

    }
}