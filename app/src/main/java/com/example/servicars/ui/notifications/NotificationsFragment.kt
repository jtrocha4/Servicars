package com.example.servicars.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicars.Quote
import com.example.servicars.adapter.QuoteAdapter
import com.example.servicars.databinding.FragmentNotificationsBinding
import com.example.servicars.ui.quote.QuoteActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot


class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    lateinit var QuoteArrayList: ArrayList<Quote>
    lateinit var Adapter: QuoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textQuote

        val currentUsuario = FirebaseAuth.getInstance().currentUser
        val emailCurrentUsuario = currentUsuario?.email

        val agregarQuote: Button = binding.agregarBtn
        agregarQuote.setOnClickListener {
            val intent = Intent(requireContext(), QuoteActivity::class.java)
            startActivity(intent)
        }

        fun onItemDelete(quote: Quote) {
            val orderReference = db.collection("user").document(emailCurrentUsuario.toString())
                .collection("quote")
            val query = orderReference.whereEqualTo("matriculaAuto", quote.matriculaAuto)
            query.get().addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    document.reference.delete().addOnSuccessListener {
                        Toast.makeText(
                            requireContext(),
                            "Cotizacion eliminada exitosamente",
                            Toast.LENGTH_SHORT
                        ).show()
                    }.addOnFailureListener {
                        Toast.makeText(
                            requireContext(), "Error al eliminar la cotizacion", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        fun EventChangeListener() {
            db.collection("user").document(emailCurrentUsuario.toString())
                .collection("quote").addSnapshotListener(object : EventListener<QuerySnapshot> {
                    override fun onEvent(
                        value: QuerySnapshot?,
                        error: FirebaseFirestoreException?
                    ) {
                        if (error != null) {
                            Log.e("Lectura FireStore", error.message.toString())
                            return
                        }
                        for (doc: DocumentChange in value?.documentChanges!!) {
                            if (doc.type == DocumentChange.Type.ADDED) {
                                QuoteArrayList.add(doc.document.toObject(Quote::class.java))
                            }
                        }
                        Adapter.notifyDataSetChanged()
                    }
                })
        }

        fun initRecyclerView() {
            binding.recyclerQuote.layoutManager = LinearLayoutManager(requireContext())
            QuoteArrayList = arrayListOf()
            Adapter =
                QuoteAdapter(QuoteArrayList, onClickDelete = { quote -> onItemDelete(quote) })
            binding.recyclerQuote.adapter = Adapter
            EventChangeListener()
        }

        initRecyclerView()

        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}