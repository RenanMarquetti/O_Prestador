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
import com.example.oprestador.common.model.Pedido

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

            val provincias = resources.getStringArray(R.array.provincias_angola)
            pedidoNovoInputProvincia.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, provincias))
            pedidoNovoInputProvincia.setOnItemClickListener { adapterView, view, i, l ->
                val municipios =  resources.getStringArray(getIdCidade(pedidoNovoInputProvincia.text.toString()))
                val adapterMunicipios = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, municipios)
                pedidoNovoInputCidade.setAdapter(adapterMunicipios)
            }

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

    private fun getIdCidade(provincia: String): Int {
        return when(provincia){
            "Bengo" -> R.array.municipios_bengo
            "Benguela" -> R.array.municipios_benguela
            "Bié" -> R.array.municipios_bie
            "Cabinda" -> R.array.municipios_cabinda
            "Cuando-Cubango" -> R.array.municipios_cuando_cubango
            "Cunene" -> R.array.municipios_cunene
            "Huambo" -> R.array.municipios_huambo
            "Huíla" -> R.array.municipios_huila
            "Kwanza Sul" -> R.array.municipios_kuanza_sul
            "Kwanza Norte" -> R.array.municipios_kuanza_norte
            "Luanda" -> R.array.municipios_luanda
            "Lunda Norte" -> R.array.municipios_lunda_norte
            "Lunda Sul" -> R.array.municipios_lunda_sul
            "Malanje" -> R.array.municipios_malange
            "Moxico" -> R.array.municipios_moxico
            "Namibe" -> R.array.municipios_namibe
            "Uíge" -> R.array.municipios_uige
            "Zaire" -> R.array.municipios_zaire
            else -> R.array.array_error
        }
    }

    private fun getIdSubCategoria(categoria: String): Int {
        return when(categoria){
            "Elétrica" -> R.array.subCategorias_eletrica
            "Hidraulica" -> R.array.subCategorias_hidraulica
            else -> R.array.array_error
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