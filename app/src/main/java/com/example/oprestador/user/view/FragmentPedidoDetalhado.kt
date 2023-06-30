package com.example.oprestador.user.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.oprestador.R
import com.example.oprestador.databinding.FragmentPedidoDetalhadoBinding
import com.example.oprestador.user.FragmentAttachListener

class FragmentPedidoDetalhado : Fragment(R.layout.fragment_pedido_detalhado) {

    private var binding:FragmentPedidoDetalhadoBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPedidoDetalhadoBinding.bind(view)

        with(binding) {

        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}