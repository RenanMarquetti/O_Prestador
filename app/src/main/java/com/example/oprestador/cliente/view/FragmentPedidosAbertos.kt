package com.example.oprestador.cliente.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oprestador.R
import com.example.oprestador.cliente.PedidosAbertos
import com.example.oprestador.cliente.presentation.PedidosAbertosPresentation
import com.example.oprestador.common.base.DependecInjector
import com.example.oprestador.common.model.Pedido
import com.example.oprestador.databinding.FragmentPedidosAbertosBinding
import com.example.oprestador.databinding.LayoutPedidosResumidoBinding

class FragmentPedidosAbertos() : Fragment(R.layout.fragment_pedidos_abertos), PedidosAbertos.View {

    private var binding: FragmentPedidosAbertosBinding? = null

    override lateinit var presenter: PedidosAbertos.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPedidosAbertosBinding.bind(view)
        presenter = PedidosAbertosPresentation(this, DependecInjector.pedidosAbertosRepository())

        binding!!.pedidosAbertosRvPedidos.layoutManager = LinearLayoutManager(requireContext())
        presenter.fetchPedidos()
        showProgess(true)

    }

    override fun showProgess(enabled: Boolean) {
        binding!!.pedidoAbertoProgressBar.isEnabled = enabled
    }

    override fun insertAdapter(pedidos: List<Pedido>) {
        binding!!.pedidosAbertosRvPedidos.adapter = PedidosAbertoAdapter(pedidos)
    }

    override fun createFailure(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private class PedidosAbertoAdapter(private val pedidos: List<Pedido>) : RecyclerView.Adapter<PedidosAbertoAdapter.ListPedidosViewHolder> () {
        val interator = pedidos.iterator()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPedidosViewHolder {
            return ListPedidosViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_pedidos_resumido,parent,false)
            )
        }

        override fun getItemCount(): Int {
            return pedidos.size
        }

        override fun onBindViewHolder(holder: ListPedidosViewHolder, position: Int) {
            holder.bind(position, interator.next())
        }

        private class ListPedidosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(posi: Int, pedido: Pedido) {
                val binding: LayoutPedidosResumidoBinding = LayoutPedidosResumidoBinding.bind(itemView)
                with(binding) {
                    layoutPedidoRedumidosTxtTitulo.text = pedido.titulo
                    layoutPedidoRedumidosTxtLocal.text = "${pedido.endereco.street} N°${pedido.endereco.numEndereco}"
                    layoutPedidoRedumidostxtCliente.text = pedido.nomeDono
                }
            }
        }
    }
}