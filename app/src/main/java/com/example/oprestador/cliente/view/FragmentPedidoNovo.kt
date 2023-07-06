package com.example.oprestador.cliente.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.oprestador.R
import com.example.oprestador.cliente.PedidoNovo
import com.example.oprestador.cliente.presentation.DadosPedido
import com.example.oprestador.cliente.presentation.PedidoNovoPresentation
import com.example.oprestador.common.base.DependecInjector
import com.example.oprestador.databinding.FragmentPedidoNovoBinding
import java.math.BigDecimal
import com.example.oprestador.common.model.Address
import com.example.oprestador.common.model.Pedido
import com.example.oprestador.common.model.UserProfile

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

            //TODO tem que terminar de fazer o gerenciamentos dos adapters desta view

            pedidoNovoBtnCriar.setOnClickListener{
                val dados = object : DadosPedido {
                    override val categoria = pedidoNovoInputCategoria.text.toString()
                    override val subCategoria = pedidoNovoInputSubCategoria.text.toString()
                    override val provincia = pedidoNovoInputProvincia.text.toString()
                    override val cidade = pedidoNovoInputCidade.text.toString()
                    override val bairro = pedidoNovoInputBairro.text.toString()
                    override val rua = pedidoNovoEditTextRua.text.toString()
                    override val numeroRua = pedidoNovoEditTextNum.text.toString()
                    override val titulo = pedidoNovoEditTextTitulo.text.toString()
                    override val descricao = pedidoNovoEditTextDescricao.text.toString()
                    override val valor = BigDecimal(pedidoNovoEditTextValor.text.toString())
                }

                presenter.createNewPedido(dados)
            }
        }
    }

    private fun getIdSubCategoria(categoria: String): Int {
        return when(categoria){
            "Elétrica" -> R.array.subCategorias_eletrica
            "Hidraulica" -> R.array.subCategorias_hidraulica
            else -> R.array.subCategorias_generica
        }
    }

    override fun showProgess(enabled: Boolean) {
        binding!!.pedidoNovoBtnCriar.showProgressBar(enabled)
    }

    override fun createDone(pedido: Pedido) {
        Toast.makeText(requireContext(), "Update de dados foi concluido", Toast.LENGTH_LONG).show()
    }

    override fun createFailure(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    override fun inputError(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }


    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}