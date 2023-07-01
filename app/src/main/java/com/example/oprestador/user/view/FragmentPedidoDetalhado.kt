package com.example.oprestador.user.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.oprestador.R
import com.example.oprestador.common.model.Pedido
import com.example.oprestador.databinding.FragmentPedidoDetalhadoBinding
import com.example.oprestador.user.FragmentAttachListener
import java.text.DateFormat
import java.text.SimpleDateFormat

class FragmentPedidoDetalhado : Fragment(R.layout.fragment_pedido_detalhado) {

    private var binding:FragmentPedidoDetalhadoBinding? = null
    private lateinit var pedido: Pedido
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPedidoDetalhadoBinding.bind(view)

        with(binding!!) {
            pedidoDetalhadoTxtTitulo.text = pedido.titulo
            pedidoDetalhadoTxtDescricao.text = pedido.descricao
            pedidoDetalhadoTxtLocal.text = pedido.endereco.street
            pedidoDetalhadoTxtPrazo.text = SimpleDateFormat("dd/MM/yyyy").format(pedido.prazo)
            pedidoDetalhadoTxtPrecoAnuncio.text = pedido.valor.toString()
        }
    }

    fun setPedido(pedido: Pedido) {
        this.pedido = pedido
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}