package com.example.oprestador.user.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.oprestador.R
import com.example.oprestador.databinding.FragmentPerfilBinding

class FragmentPerfil : Fragment(R.layout.fragment_perfil) {

    private var binding: FragmentPerfilBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPerfilBinding.bind(view)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}