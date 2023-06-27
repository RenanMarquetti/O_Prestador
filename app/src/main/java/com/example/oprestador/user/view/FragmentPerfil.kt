package com.example.oprestador.user.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.oprestador.R
import com.example.oprestador.common.base.DependecInjector
import com.example.oprestador.common.model.UserProfile
import com.example.oprestador.databinding.FragmentPerfilBinding
import com.example.oprestador.user.Perfil
import com.example.oprestador.user.presentation.PerfilPresentation

class FragmentPerfil : Fragment(R.layout.fragment_perfil), Perfil.View {

    private var binding: FragmentPerfilBinding? = null

    override lateinit var presenter : Perfil.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPerfilBinding.bind(view)
        presenter = PerfilPresentation(this, DependecInjector.userRepository())

        with(binding!!) {

            perfilCheckboxConcentimento.setOnClickListener {
                perfilLoadingButton.isEnabled = perfilCheckboxConcentimento.isChecked
            }

            perfilLoadingButton.setOnClickListener {
                presenter.updateProfile()
            }

        }
    }

    override fun showProgess(enabled: Boolean) {
        binding!!.perfilLoadingButton.showProgressBar(enabled)
    }

    override fun updateDone(profile: UserProfile) {

    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }
}