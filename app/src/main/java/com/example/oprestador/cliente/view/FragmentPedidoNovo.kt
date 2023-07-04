package com.example.oprestador.cliente.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.oprestador.R
import com.example.oprestador.cliente.PedidoNovo
import com.example.oprestador.cliente.presentation.DadosPedido
import com.example.oprestador.cliente.presentation.PedidoNovoPresentation
import com.example.oprestador.common.base.DependecInjector
import com.example.oprestador.databinding.FragmentPedidoNovoBinding
import java.math.BigDecimal
import com.example.oprestador.common.model.Address

class FragmentPedidoNovo : Fragment(R.layout.fragment_pedido_novo), PedidoNovo.View {

    private var binding: FragmentPedidoNovoBinding? = null
    override lateinit var presenter: PedidoNovo.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPedidoNovoBinding.bind(view)
        presenter = PedidoNovoPresentation(this, DependecInjector.pedidoNovoRepository())

        val categorias = resources.getStringArray(R.array.categorias)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, categorias)

        with(binding!!) {
            pedidoNovoInputCategoria.setAdapter(adapter)
            pedidoNovoInputCategoria.setOnItemClickListener { adapterView, view, i, l ->
                val subCategorias = resources.getStringArray(getIdSubCategoria(pedidoNovoInputCategoria.text.toString()))
                val adapterSubCategorias = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, subCategorias)
                pedidoNovoInputSubCategoria.setText("Escolha uma Sub Categoria")
                pedidoNovoInputSubCategoria.setAdapter(adapterSubCategorias)
            }

            pedidoNovoBtnCriar.setOnClickListener{
                object : DadosPedido {
                    override val categoria = pedidoNovoInputCategoria.text.toString()
                    override val subCategoria = pedidoNovoInputSubCategoria.text.toString()
                    override val address = Address(pedidoNovoEditTextLocal.text.toString(),"","","")
                    override val titulo = pedidoNovoEditTextTitulo.text.toString()
                    override val descricao = pedidoNovoEditTextDescricao.text.toString()
                    override val valor = BigDecimal(pedidoNovoEditTextValor.text.toString())
                }
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