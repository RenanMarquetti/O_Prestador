package com.example.oprestador.user.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.oprestador.R
import com.example.oprestador.databinding.FragmentPedidoDetalhadoBinding

class FragmentPedidoDetalhado : Fragment(R.layout.fragment_pedido_detalhado) {

    private var binding:FragmentPedidoDetalhadoBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPedidoDetalhadoBinding.bind(view)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}