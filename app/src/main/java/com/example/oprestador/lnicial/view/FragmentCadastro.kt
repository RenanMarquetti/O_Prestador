package com.example.oprestador.lnicial.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.oprestador.R
import com.example.oprestador.databinding.FragmentCadastroBinding

class FragmentCadastro : Fragment(R.layout.fragment_cadastro) {

    private var binding: FragmentCadastroBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCadastroBinding.bind(view)

        with(binding!!) {

            btnCadastro.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_fragmentCadastro_to_fragmentDivisor)
            }

        }

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}
