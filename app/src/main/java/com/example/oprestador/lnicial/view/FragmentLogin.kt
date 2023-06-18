package com.example.oprestador.lnicial.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.oprestador.R
import com.example.oprestador.common.TxtWatcher
import com.example.oprestador.databinding.FragmentLoginBinding
import com.example.oprestador.lnicial.Login


class FragmentLogin : Fragment(R.layout.fragment_login), Login.View {

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

            loginEditEmail.addTextChangedListener(watcher)
            loginEditPassword.addTextChangedListener(watcher)
        }
    }

    private val watcher = TxtWatcher{
        binding!!.btnAcessar.isEnabled = it.isNotEmpty()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    override fun showProgeess(enabled: Boolean) {
        TODO("Not yet implemented")
    }

    override fun displayEmailFailure(emailError: Int?) {
        TODO("Not yet implemented")
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        TODO("Not yet implemented")
    }

    override fun onUserAuthenticated() {
        TODO("Not yet implemented")
    }

    override fun onUserUnauthorized() {
        TODO("Not yet implemented")
    }
}
