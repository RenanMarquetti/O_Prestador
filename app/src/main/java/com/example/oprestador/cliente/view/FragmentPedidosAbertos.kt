package com.example.oprestador.cliente.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oprestador.R
import com.example.oprestador.common.model.Database
import com.example.oprestador.common.model.Pedido
import com.example.oprestador.databinding.FragmentPedidosAbertosBinding
import com.example.oprestador.databinding.LayoutPedidosResumidoBinding

class FragmentPedidosAbertos : Fragment(R.layout.fragment_pedidos_abertos) {

    private var binding: FragmentPedidosAbertosBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPedidosAbertosBinding.bind(view)

        val rv = binding!!.pedidosAbertosRvPedidos
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = PedidosAbertoAdapter()

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private class PedidosAbertoAdapter : RecyclerView.Adapter<PedidosAbertoAdapter.ListPedidosViewHolder> () {

        //private val interatorPedidos = Database.sessionAuth!!.profile.listPedidosComprados.iterator()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPedidosViewHolder {
            return ListPedidosViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_pedidos_resumido,parent,false)
            )
        }

        override fun getItemCount(): Int {
            //return Database.sessionAuth!!.profile.listPedidosComprados.size
            return 0
        }

        override fun onBindViewHolder(holder: ListPedidosViewHolder, position: Int) {
            //holder.bind(position, interatorPedidos.next())
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