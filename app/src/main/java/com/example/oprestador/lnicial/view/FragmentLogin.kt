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
import com.example.oprestador.user.view.UserActivity


class FragmentLogin : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.findViewById<Button>(R.id.btn_acessar).setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.action_fragmentLogin_to_fragmentDivisor)

//            val intent = Intent(context, UserActivity::class.java)
//            startActivity(intent)
        }

        view.findViewById<TextView>(R.id.txtCadastro).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragmentLogin_to_fragmentCadastro)
        }

        return view


    }
}
