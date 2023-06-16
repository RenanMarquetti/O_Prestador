package com.example.oprestador.lnicial.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.oprestador.R

class FragmentCadastro : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_cadastro, container, false)

        view.findViewById<Button>(R.id.btn_cadastro).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragmentCadastro_to_fragmentDivisor)
        }
        return view

    }
}
