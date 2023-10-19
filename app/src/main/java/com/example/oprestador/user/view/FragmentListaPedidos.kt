package com.example.oprestador.user.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oprestador.R
import com.example.oprestador.common.base.DependecInjector
import com.example.oprestador.common.model.Database
import com.example.oprestador.common.model.Pedido
import com.example.oprestador.databinding.FragmentListaPedidosBinding
import com.example.oprestador.databinding.LayoutPedidosResumidoBinding
import com.example.oprestador.user.FragmentAttachListener
import com.example.oprestador.user.ListaPedido
import com.example.oprestador.user.presentation.ListaPedidoPresentation

class FragmentListaPedidos : Fragment(R.layout.fragment_lista_pedidos), ListaPedido.View {

    private var binding: FragmentListaPedidosBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null

    override lateinit var presenter: ListaPedido.Presenter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListaPedidosBinding.bind(view)
        presenter = ListaPedidoPresentation(this, DependecInjector.userRepository())

        with(binding!!) {

            listaPedidosTxtQtdMoedas.text = Database.sessionUser!!.moedas.toString()

            listaPedidosRvPedidos.layoutManager = LinearLayoutManager(requireContext())

        }

        presenter.getListFeed()

    }

    override fun setAdapter(pedidos: List<Pedido>) {
        binding!!.listaPedidosRvPedidos.adapter = ListPedidosAdapter(pedidos, fragmentAttachListener!!)
    }

    override fun inputError(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        binding!!.listaPedidosTxtQtdMoedas.text = Database.sessionUser!!.moedas.toString()
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

    private class ListPedidosAdapter(private val pedidos: List<Pedido>, private val fragmentAttachListener: FragmentAttachListener) : RecyclerView.Adapter<ListPedidosAdapter.ListPedidosViewHolder> () {

        private val interatorPedidos = pedidos.iterator()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPedidosViewHolder {
            return ListPedidosViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_pedidos_resumido,parent,false)
            )
        }

        override fun getItemCount(): Int {
            return pedidos.size
        }

        override fun onBindViewHolder(holder: ListPedidosViewHolder, position: Int) {
            holder.bind(position, interatorPedidos.next(), fragmentAttachListener)
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