package com.example.servicars.ui.quote

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.servicars.databinding.ActivityQuoteBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate


class QuoteActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityQuoteBinding
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityQuoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinnerEstadoCotizacion = binding.spinnerEstadoCotizacion
        val optionsEstadoCotizacion = arrayOf("Estado de la cotizacion", "Ingreso", "Gasto")
        spinnerEstadoCotizacion.adapter = ArrayAdapter<String>(
            this, R.layout.simple_list_item_1, optionsEstadoCotizacion
        )

        val currentUsuario = FirebaseAuth.getInstance().currentUser
        val emailCurrentUsuario = currentUsuario?.email

        fun limpiarInputs(view: View) {
            val editMarcaAuto = binding.marcaAutoEditText
            val editModeloAuto = binding.modeloAutoEditText
            val editAnioAuto = binding.anioAutoEditText
            val editMatriculaAuto = binding.matriculaAutoEditText
            val editMontoQuote = binding.montoEditText
            val editDescripcionQuote = binding.detalleCotizacionEditText

            editMarcaAuto.setText("")
            editModeloAuto.setText("")
            editAnioAuto.setText("")
            editMatriculaAuto.setText("")
            editMontoQuote.setText("")
            editDescripcionQuote.setText("")
        }

        fun guardarQuote(view: View) {

            val editMarcaAuto = binding.marcaAutoEditText
            val editModeloAuto = binding.modeloAutoEditText
            val editAnioAuto = binding.anioAutoEditText
            val editMatriculaAuto = binding.matriculaAutoEditText
            val editMontoQuote = binding.montoEditText
            val editDescripcionQuote = binding.detalleCotizacionEditText

            val marcaAuto = editMarcaAuto.text.toString()
            val modeloAuto = editModeloAuto.text.toString()
            val anioAuto = editAnioAuto.text.toString()
            val matriculaAuto = editMatriculaAuto.text.toString()
            val montoQuote = editMontoQuote.text.toString()
            val descripcionQuote = editDescripcionQuote.text.toString()

            if (marcaAuto.isEmpty()) {
                editMarcaAuto.error = "Ingrese la marca del automovil"
                editMarcaAuto.requestFocus()
                return
            }
            if (modeloAuto.isEmpty()) {
                editModeloAuto.error = "Ingrese el modelo del automovil"
                editModeloAuto.requestFocus()
                return
            }
            if (anioAuto.isEmpty()) {
                editAnioAuto.error = "Ingrese el modelo del automovil"
                editAnioAuto.requestFocus()
                return
            }
            if (matriculaAuto.isEmpty()) {
                editMatriculaAuto.error = "Ingrese la matricula del automovil"
                editMatriculaAuto.requestFocus()
                return
            }
            if (montoQuote.isEmpty()) {
                editMontoQuote.error = "Ingrese el monto de la cotizacion"
                editMontoQuote.requestFocus()
                return
            }
            if (descripcionQuote.isEmpty()) {
                editDescripcionQuote.error = "Ingrese el detalle de la informacion"
                editDescripcionQuote.requestFocus()
                return
            }

            val quote = hashMapOf(
                "marcaAuto" to marcaAuto,
                "modeloAuto" to modeloAuto,
                "anioAuto" to anioAuto.toInt(),
                "matriculaAuto" to matriculaAuto,
                "montoQuote" to montoQuote.toInt(),
                "fechaQuote" to LocalDate.now().toString(),
                "estadoQuote" to spinnerEstadoCotizacion.selectedItem.toString(),
                "descripcionQuote" to descripcionQuote
            )

            val userCollectioRef = db.collection("user").document(emailCurrentUsuario.toString())
            val quoteCollectionRef = userCollectioRef.collection("quote").add(quote)
            Toast.makeText(this, "Cotizacion guardada exitosamente", Toast.LENGTH_SHORT).show()
            limpiarInputs(view)
        }

        val guardarQuoteBtn = binding.guardarBtn
        guardarQuoteBtn.setOnClickListener(::guardarQuote)

        val cancelarQuoteBtn = binding.cancelarBtn
        cancelarQuoteBtn.setOnClickListener(::limpiarInputs)

    }
}