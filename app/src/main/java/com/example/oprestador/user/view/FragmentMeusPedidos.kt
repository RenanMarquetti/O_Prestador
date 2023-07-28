package com.example.oprestador.user.view

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
import com.example.oprestador.databinding.FragmentMeusPedidosBinding
import com.example.oprestador.databinding.LayoutPedidosResumidoBinding
import com.example.oprestador.user.MeusPedidos
import com.example.oprestador.user.presentation.MeusPedidosPresentation

class FragmentMeusPedidos : Fragment(R.layout.fragment_meus_pedidos), MeusPedidos.View {

    private var binding: FragmentMeusPedidosBinding? = null
    override lateinit var presenter : MeusPedidos.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMeusPedidosBinding.bind(view)
        presenter = MeusPedidosPresentation(this, DependecInjector.userRepository())

        with(binding!!) {
            meusPedidosRvPedidos.layoutManager = LinearLayoutManager(requireContext())
        }

        presenter.getMeusPedidos()
    }

    override fun setAdapter(pedidos: List<Pedido>) {
        binding!!.meusPedidosRvPedidos.adapter = MeusPedidosAdapter(pedidos)
    }

    override fun showFailure(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private class MeusPedidosAdapter(private val pedidos: List<Pedido>) : RecyclerView.Adapter<MeusPedidosAdapter.ListPedidosViewHolder> () {

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
            holder.bind(position, interatorPedidos.next())
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