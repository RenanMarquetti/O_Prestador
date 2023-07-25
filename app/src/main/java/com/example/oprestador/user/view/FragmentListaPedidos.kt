package com.example.oprestador.user.view

import android.content.Context
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
import com.example.oprestador.databinding.FragmentListaPedidosBinding
import com.example.oprestador.databinding.LayoutPedidosResumidoBinding
import com.example.oprestador.user.FragmentAttachListener

class FragmentListaPedidos : Fragment(R.layout.fragment_lista_pedidos) {

    private var binding: FragmentListaPedidosBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListaPedidosBinding.bind(view)

        with(binding!!) {

            listaPedidosTxtQtdMoedas.text = Database.sessionAuth!!.moedas.toString()

            listaPedidosRvPedidos.layoutManager = LinearLayoutManager(requireContext())
            listaPedidosRvPedidos.adapter = ListPedidosAdapter(fragmentAttachListener!!)
        }

    }

    override fun onResume() {
        super.onResume()
        binding!!.listaPedidosTxtQtdMoedas.text = Database.sessionAuth!!.moedas.toString()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListener) fragmentAttachListener = context
    }

    override fun onDestroy() {
        binding = null
        fragmentAttachListener = null
        super.onDestroy()
    }

    private class ListPedidosAdapter(fragmentAttachListener: FragmentAttachListener) : RecyclerView.Adapter<ListPedidosAdapter.ListPedidosViewHolder> () {

        private val interatorPedidos = Database.pedidosList.iterator()
        val listener = fragmentAttachListener

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPedidosViewHolder {
            return ListPedidosViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_pedidos_resumido,parent,false)
            )
        }

        override fun getItemCount(): Int {
            return Database.pedidosList.size
        }

        override fun onBindViewHolder(holder: ListPedidosViewHolder, position: Int) {
            holder.bind(position, interatorPedidos.next(), listener)
        }

        private class ListPedidosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(posi: Int, pedido: Pedido, listener: FragmentAttachListener) {

                val binding: LayoutPedidosResumidoBinding = LayoutPedidosResumidoBinding.bind(itemView)
                with(binding) {
                    layoutPedidoRedumidosTxtTitulo.text = pedido.titulo
                    layoutPedidoRedumidosTxtLocal.text = "${pedido.endereco.street} N°${pedido.endereco.numEndereco}"
                    layoutPedidoRedumidostxtCliente.text = pedido.nomeDono
                }

                //val containsListUser = Database.sessionAuth!!.profile.listPedidosComprados.contains(pedido)
                val containsListUser = HashSet<Pedido>().contains(pedido)

                if(!containsListUser ) {
                    itemView.isEnabled = true
                    itemView.setOnClickListener {
                        listener.goToPedidoDetalhadoScrean(pedido)
                    }
                } else {
                    itemView.isEnabled = false
                }
            }
        }
    }
}