package com.example.oprestador.user.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.oprestador.R
import com.example.oprestador.common.base.DependecInjector
import com.example.oprestador.common.model.Database
import com.example.oprestador.common.model.UserProfile
import com.example.oprestador.databinding.FragmentPerfilBinding
import com.example.oprestador.user.Perfil
import com.example.oprestador.user.presentation.DadosProfile
import com.example.oprestador.user.presentation.PerfilPresentation

class FragmentPerfil : Fragment(R.layout.fragment_perfil), Perfil.View {

    private var binding: FragmentPerfilBinding? = null

    override lateinit var presenter : Perfil.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPerfilBinding.bind(view)
        presenter = PerfilPresentation(this, DependecInjector.userRepository())

        inserirDados()

        with(binding!!) {

            perfilCheckboxConcentimento.setOnClickListener {
                perfilLoadingButtonSalvar.isEnabled = perfilCheckboxConcentimento.isChecked
            }

            perfilLoadingButtonSalvar.setOnClickListener {

                val dados = object : DadosProfile {
                    override val name = perfilEditNomeCompleto.text.toString()
                    override val ddd = perfilEditDdd.text.toString()
                    override val telefone = perfilEditTelefone.text.toString()
                    override val street = perfilEditEndereco.text.toString()
                    override val numStreet = perfilEditNumEndereco.text.toString()
                    override val city = perfilEditCidade.text.toString()
                    override val bairro = perfilEditBairro.text.toString()
                }

                presenter.updateProfile(dados)
            }
        }
    }

    private fun inserirDados() {
        with(Database.sessionUser!!.profile) {
            binding!!.perfilEditNomeCompleto.setText(name)
            binding!!.perfilEditDdd.setText(telefone.ddd)
            binding!!.perfilEditTelefone.setText(telefone.telefone)
            binding!!.perfilEditEndereco.setText(endereco.street)
            binding!!.perfilEditNumEndereco.setText(endereco.numEndereco)
            binding!!.perfilEditCidade.setText(endereco.city)
            binding!!.perfilEditBairro.setText(endereco.neighborhood)

        }
    }

    override fun showProgess(enabled: Boolean) {
        binding!!.perfilLoadingButtonSalvar.showProgressBar(enabled)
    }

    override fun updateDone(profile: UserProfile) {
        Toast.makeText(requireContext(), "Update de dados foi concluido", Toast.LENGTH_LONG).show()
    }

    override fun updateFailure(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    override fun inputError(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }
}