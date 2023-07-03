package com.example.oprestador.cliente.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.oprestador.R
import com.example.oprestador.databinding.FragmentPedidoNovoBinding

class FragmentPedidoNovo : Fragment(R.layout.fragment_pedido_novo) {

    private var binding: FragmentPedidoNovoBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPedidoNovoBinding.bind(view)

        val categorias = resources.getStringArray(R.array.categorias)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, categorias)

        with(binding!!) {
            pedidoNovoInputCategoria.setAdapter(adapter)
            pedidoNovoInputCategoria.setOnItemClickListener { adapterView, view, i, l ->
                val subCategorias = resources.getStringArray(getIdSubCategoria(pedidoNovoInputCategoria.text.toString()))
                val adapterSubCategorias = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, subCategorias)
                inputSubCategoria.setText("Escolha uma Sub Categoria")
                inputSubCategoria.setAdapter(adapterSubCategorias)
            }
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun getIdSubCategoria(categoria: String): Int {
        return when(categoria){
            "Elétrica" -> R.array.subCategorias_eletrica
            "Hidraulica" -> R.array.subCategorias_hidraulica
            else -> R.array.subCategorias_generica
        }
    }
}