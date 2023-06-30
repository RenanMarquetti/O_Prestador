package com.example.oprestador.user.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oprestador.R
import com.example.oprestador.common.model.Database
import com.example.oprestador.common.model.Pedido
import com.example.oprestador.databinding.FragmentListaPedidosBinding
import com.example.oprestador.databinding.LayoutPedidosResumidoBinding

class FragmentListaPedidos : Fragment(R.layout.fragment_lista_pedidos) {

    private var binding: FragmentListaPedidosBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListaPedidosBinding.bind(view)

        with(binding!!) {

            listaPedidosTxtQtdMoedas.text = Database.sessionProfile!!.moedas.toString()

            listaPedidosRvPedidos.layoutManager = LinearLayoutManager(requireContext())
            listaPedidosRvPedidos.adapter = ListPedidosAdapter()
        }

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private class ListPedidosAdapter : RecyclerView.Adapter<ListPedidosAdapter.ListPedidosViewHolder> () {
        private val interatorPedidos = Database.pedidosList.iterator()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPedidosViewHolder {
            return ListPedidosViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_pedidos_resumido,parent,false)
            )
        }

        override fun getItemCount(): Int {
            return Database.pedidosList.size
        }

        override fun onBindViewHolder(holder: ListPedidosViewHolder, position: Int) {
            holder.bind(position, interatorPedidos.next())
        }

        private class ListPedidosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


            fun bind(posi: Int, pedido: Pedido) {

                val binding: LayoutPedidosResumidoBinding = LayoutPedidosResumidoBinding.bind(itemView)
                with(binding) {
                    layoutPedidoRedumidosTxtTitulo.text = pedido.titulo
                    layoutPedidoRedumidosTxtLocal.text = "${pedido.endereco.street} N°${pedido.endereco.numEndereco}"
                    layoutPedidoRedumidostxtCliente.text = pedido.nomeCliente
                }

                itemView.setOnClickListener {
                    Navigation.findNavController(it).navigate(R.id.action_nav_fragmentListaPedidos_to_fragmentPedidoDetalhado)
                }
            }
        }
    }
}