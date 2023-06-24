package com.example.oprestador.lnicial.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.oprestador.R
import com.example.oprestador.common.base.DependecInjector
import com.example.oprestador.common.model.UserAuth
import com.example.oprestador.common.view.TxtWatcher
import com.example.oprestador.databinding.FragmentCadastroBinding
import com.example.oprestador.lnicial.presentation.CadastroPresentation

class FragmentCadastro : Fragment(R.layout.fragment_cadastro), Cadastro.View {

    private var binding: FragmentCadastroBinding? = null
    override lateinit var presenter: Cadastro.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCadastroBinding.bind(view)
        presenter = CadastroPresentation(this, DependecInjector.cadastroRepository())

        with(binding!!) {

            cadastroEditEmail.addTextChangedListener(watcher)
            cadastroEditEmail.addTextChangedListener(TxtWatcher {
                displayEmailFailure(null)
            })

            cadastroEditPassword.addTextChangedListener(watcher)
            cadastroEditPassword.addTextChangedListener(TxtWatcher {
                displayPasswordFailure(null)
            })

            cadastroEditReptPassword.addTextChangedListener(watcher)
            cadastroEditReptPassword.addTextChangedListener(TxtWatcher {
                displayRepetPasswordFailure(null)
            })

            cadastroBtnCadastrar.setOnClickListener {
                presenter.create(cadastroEditEmail.text.toString(), cadastroEditPassword.text.toString(), cadastroEditReptPassword.text.toString())
            }

        }

    }

    private val watcher = TxtWatcher{
        binding!!.cadastroBtnCadastrar.isEnabled = binding!!.cadastroEditEmail.text.toString().isNotEmpty()
                && binding!!.cadastroEditPassword.text.toString().isNotEmpty()
                && binding!!.cadastroEditReptPassword.text.toString().isNotEmpty()
    }

    override fun showProgeess(enabled: Boolean) {
        binding!!.cadastroBtnCadastrar.showProgressBar(enabled)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding!!.cadastroEditEmailInput.error = emailError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding!!.cadastroEditPasswordInput.error = passwordError?.let { getString(it) }
    }

    override fun displayRepetPasswordFailure(repetPasswordError: Int?) {
        binding!!.cadastroEditReptPasswordInput.error = repetPasswordError?.let { getString(it) }
    }

    override fun onUserCreated(user: UserAuth) {
        Navigation.findNavController(requireView()).navigate(R.id.action_fragmentCadastro_to_fragmentDivisor)
    }

    override fun onUserUnCreated(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}
