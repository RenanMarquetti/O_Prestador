package com.example.oprestador.lnicial.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.oprestador.R
import com.example.oprestador.common.view.TxtWatcher
import com.example.oprestador.databinding.FragmentLoginBinding
import com.example.oprestador.lnicial.Login
import com.example.oprestador.lnicial.presentation.LoginPresenter


class FragmentLogin : Fragment(R.layout.fragment_login), Login.View {

    private var binding: FragmentLoginBinding? = null
    override lateinit var presenter: Login.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)
        presenter = LoginPresenter(this)

        with(binding!!) {
            loginLoadingButton.setOnClickListener {
                showProgeess(true)

                presenter.login(loginEditEmail.text.toString(),loginEditPassword.text.toString())
            }

            txtCadastro.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_fragmentLogin_to_fragmentCadastro)
            }

            loginEditEmail.addTextChangedListener(watcher)
            loginEditEmail.addTextChangedListener(TxtWatcher {
                displayEmailFailure(null)
            })

            loginEditPassword.addTextChangedListener(watcher)
            loginEditPassword.addTextChangedListener(TxtWatcher {
                displayPasswordFailure(null)
            })
        }
    }

    private val watcher = TxtWatcher{
        binding!!.loginLoadingButton.isEnabled = binding!!.loginEditEmail.text.toString().isNotEmpty()
                && binding!!.loginEditPassword.text.toString().isNotEmpty()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        binding = null
        super.onDestroy()
    }

    override fun showProgeess(enabled: Boolean) {
        binding!!.loginLoadingButton.showProgressBar(enabled)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding!!.loginEditEmailInput.error = emailError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding!!.loginEditPasswordInput.error = passwordError?.let { getString(it) }
    }

    override fun onUserAuthenticated() {
        Navigation.findNavController(requireView()).navigate(R.id.action_fragmentLogin_to_fragmentDivisor)
    }

    override fun onUserUnauthorized(message: String) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }
}
