package com.example.servicars.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicars.Order
import com.example.servicars.OrdersProvider
import com.example.servicars.adapter.OrdersApadter
import com.example.servicars.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome

        fun onItemSelected(order: Order) {
            Toast.makeText(requireContext(), order.vehiculo, Toast.LENGTH_SHORT).show()
        }

        fun initRecyclerView() {
            binding.recyclerOrders.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerOrders.adapter = OrdersApadter(
                OrdersProvider.OrderList
            ) { order -> onItemSelected(order) }
        }

        initRecyclerView()

        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}