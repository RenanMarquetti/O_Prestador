package com.example.oprestador.common.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.oprestador.R
import com.example.oprestador.common.model.Pedido

open class DefaultAdapterPedidos(private val pedidos: List<Pedido>) : RecyclerView.Adapter<DefaultAdapterPedidos.ListPedidosViewHolder> () {

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

    public class ListPedidosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(posi: Int, pedido: Pedido) {

        }
    }
}