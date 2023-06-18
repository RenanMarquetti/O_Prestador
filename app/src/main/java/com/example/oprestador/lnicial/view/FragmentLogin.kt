package com.example.oprestador.lnicial.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.oprestador.R
import com.example.oprestador.databinding.FragmentLoginBinding
import com.example.oprestador.user.view.UserActivity


class FragmentLogin : Fragment(R.layout.fragment_login) {

    private var binding: FragmentLoginBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        with(binding!!) {
            btnAcessar.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_fragmentLogin_to_fragmentDivisor)
            }

            txtCadastro.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_fragmentLogin_to_fragmentCadastro)
            }
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}
