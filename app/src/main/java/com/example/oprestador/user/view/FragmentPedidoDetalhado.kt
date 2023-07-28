package com.example.oprestador.user.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.oprestador.R
import com.example.oprestador.common.base.DependecInjector
import com.example.oprestador.common.model.Database
import com.example.oprestador.common.model.Pedido
import com.example.oprestador.databinding.FragmentPedidoDetalhadoBinding
import com.example.oprestador.user.PedidoDetalhado
import com.example.oprestador.user.presentation.PedidoDetalhadoPresentation
import java.text.SimpleDateFormat

class FragmentPedidoDetalhado : Fragment(R.layout.fragment_pedido_detalhado), PedidoDetalhado.View {

    private val user = Database.sessionUser!!

    private var binding:FragmentPedidoDetalhadoBinding? = null
    override lateinit var presenter: PedidoDetalhado.Presenter

    private lateinit var pedido: Pedido
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPedidoDetalhadoBinding.bind(view)
        presenter = PedidoDetalhadoPresentation(this, DependecInjector.pedidoDetalhadoRepository())

        with(binding!!) {
            pedidoDetalhadoTxtTitulo.text = pedido.titulo
            pedidoDetalhadoTxtDescricao.text = pedido.descricao
            pedidoDetalhadoTxtLocal.text = pedido.endereco.street
            pedidoDetalhadoTxtPrazo.text = SimpleDateFormat("dd/MM/yyyy").format(pedido.prazo)
            pedidoDetalhadoTxtPrecoAnuncio.text = pedido.valorAnuncio.toString()
            pedidoDetalhadoLayoutButtonLiberarPedido.setOnClickListener{

                if(pedido.valorAnuncio <= user.moedas) {
                    Database.sessionUser!!.moedas -= pedido.valorAnuncio
                    presenter.comprarPedido(pedido)
                    //pedido.listUuidUsersQueCompraram.add(user.uuid!!)
                } else {
                    pedidoDetalhadoLayoutButtonLiberarPedido.isEnabled = false
                    showFailure("Moedas Insuficientes")
                }

            }
        }
    }

    fun setPedido(pedido: Pedido) {
        this.pedido = pedido
    }

    override fun showFailure(msg: String) {
        Toast.makeText(requireContext(),msg, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToFragmentListaPedidos() {
        Navigation.findNavController(requireView()).navigate(R.id.nav_fragmentListaPedidos)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}