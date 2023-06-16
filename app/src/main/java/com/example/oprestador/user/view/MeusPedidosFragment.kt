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

class MeusPedidosFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_meus_pedidos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = view.findViewById<RecyclerView>(R.id.meusPedidos_rv_pedidos)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = MeusPedidosAdapter()

    }

    private class MeusPedidosAdapter : RecyclerView.Adapter<MeusPedidosAdapter.ListPedidosViewHolder> () {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPedidosViewHolder {
            return ListPedidosViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_pedidos_resumido,parent,false)
            )
        }

        override fun getItemCount(): Int {
            return 3
        }

        override fun onBindViewHolder(holder: ListPedidosViewHolder, position: Int) {
            holder.bind(position)
        }

        private class ListPedidosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(posi: Int) {
                itemView.findViewById<TextView>(R.id.layoutPedidoRedumidos_txt_titulo).setText("Titulo ${posi+1}")
                itemView.findViewById<TextView>(R.id.layoutPedidoRedumidostxt_cliente).setText("Nome Cliente ${posi+1}")
                itemView.findViewById<TextView>(R.id.layoutPedidoRedumidos_txt_local).setText("Local ${posi+1}")
            }
        }
    }
}