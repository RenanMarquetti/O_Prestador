package com.example.oprestador.lnicial.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.oprestador.R
import com.example.oprestador.cliente.view.ClienteActivity
import com.example.oprestador.databinding.FragmentDivisorBinding
import com.example.oprestador.user.view.UserActivity

class FragmentDivisor : Fragment(R.layout.fragment_divisor) {

    private var binding: FragmentDivisorBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDivisorBinding.bind(view)

        with(binding!!) {
            logo.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_fragmentDivisor_to_fragmentLogin)
            }

            btnDivisorPedirServico.setOnClickListener {
                startActivity(Intent(context, ClienteActivity::class.java))
            }

            btnDivisorCadastrarServico.setOnClickListener {
                startActivity(Intent(context,UserActivity::class.java))
            }
        }
    }

    override fun startActivity(intent: Intent?) {
        intent?.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        super.startActivity(intent)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}
