package com.example.oprestador.user.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oprestador.R
import com.example.oprestador.common.model.Database
import com.example.oprestador.common.model.Pedido
import com.example.oprestador.databinding.FragmentMeusPedidosBinding
import com.example.oprestador.databinding.LayoutPedidosResumidoBinding

class FragmentMeusPedidos : Fragment(R.layout.fragment_meus_pedidos) {

    private var binding: FragmentMeusPedidosBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMeusPedidosBinding.bind(view)

        with(binding!!) {

            meusPedidosRvPedidos.layoutManager = LinearLayoutManager(requireContext())
            meusPedidosRvPedidos.adapter = MeusPedidosAdapter()
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private class MeusPedidosAdapter : RecyclerView.Adapter<MeusPedidosAdapter.ListPedidosViewHolder> () {

        private val interatorPedidos = Database.sessionProfile!!.listPedidos.iterator()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPedidosViewHolder {
            return ListPedidosViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_pedidos_resumido,parent,false)
            )
        }

        override fun getItemCount(): Int {
            return Database.sessionProfile!!.listPedidos.size
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
            }
        }
    }
}