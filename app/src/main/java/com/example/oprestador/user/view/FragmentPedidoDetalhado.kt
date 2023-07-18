package com.example.oprestador.user.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.oprestador.R
import com.example.oprestador.common.model.Database
import com.example.oprestador.common.model.Pedido
import com.example.oprestador.databinding.FragmentPedidoDetalhadoBinding
import java.text.SimpleDateFormat

class FragmentPedidoDetalhado : Fragment(R.layout.fragment_pedido_detalhado) {

    private val user = Database.sessionAuth!!
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
            pedidoDetalhadoTxtPrecoAnuncio.text = pedido.valorAnuncio.toString()
            pedidoDetalhadoLayoutButtonLiberarPedido.setOnClickListener{
                 if(pedido.valorAnuncio <= user.moedas) {
                     Database.sessionAuth!!.moedas -= pedido.valorAnuncio
                     user.profile.listPedidosComprados.add(pedido)
                     Navigation.findNavController(view).navigate(R.id.nav_fragmentListaPedidos)
                 } else {
                     Toast.makeText(requireContext(),"Moedas Insuficientes", Toast.LENGTH_SHORT).show()
                 }

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