package com.example.oprestador.user.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.oprestador.R
import com.example.oprestador.common.model.Database
import com.example.oprestador.common.model.Pedido
import com.example.oprestador.databinding.FragmentPedidoDetalhadoBinding
import com.example.oprestador.user.FragmentAttachListener
import java.text.DateFormat
import java.text.SimpleDateFormat

class FragmentPedidoDetalhado : Fragment(R.layout.fragment_pedido_detalhado) {

    private val profile = Database.sessionProfile!!
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
            pedidoDetalhadoLayoutButtonLiberarPedido.isClickable = true //pedido.valor <= profile.moedas
            pedidoDetalhadoLayoutButtonLiberarPedido.setOnClickListener{
                profile.moedas -= pedido.valor
                profile.listPedidos.add(pedido)
                Navigation.findNavController(view).navigate(R.id.nav_fragmentListaPedidos)
            }
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