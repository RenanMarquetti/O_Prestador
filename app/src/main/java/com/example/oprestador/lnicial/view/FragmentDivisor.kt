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
import com.example.oprestador.user.view.UserActivity

class FragmentDivisor : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_divisor, container, false)

        view.findViewById<ImageView>(R.id.logo).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragmentDivisor_to_fragmentLogin)
        }

        view.findViewById<Button>(R.id.btn_divisor_pedir_servico).setOnClickListener {

            val intent = Intent(context, ClienteActivity::class.java)
            startActivity(intent)
        }

        view.findViewById<Button>(R.id.btn_divisor_cadastrar_servico).setOnClickListener {

            val intent = Intent(context,UserActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
