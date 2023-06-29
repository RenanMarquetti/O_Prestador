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
import com.example.oprestador.databinding.FragmentListaPedidosBinding

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

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPedidosViewHolder {
            return ListPedidosViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_pedidos_resumido,parent,false)
            )
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: ListPedidosViewHolder, position: Int) {
            holder.bind(position)
        }

        private class ListPedidosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(posi: Int) {
                itemView.findViewById<TextView>(R.id.layoutPedidoRedumidos_txt_titulo).setText("Titulo ${posi+1}")
                itemView.findViewById<TextView>(R.id.layoutPedidoRedumidostxt_cliente).setText("Nome Cliente ${posi+1}")
                itemView.findViewById<TextView>(R.id.layoutPedidoRedumidos_txt_local).setText("Local ${posi+1}")
                itemView.setOnClickListener {
                    Navigation.findNavController(it).navigate(R.id.action_nav_fragmentListaPedidos_to_fragmentPedidoDetalhado)
                }
            }
        }
    }
}