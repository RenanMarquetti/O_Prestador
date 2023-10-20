package com.example.oprestador.cliente.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.oprestador.R
import com.example.oprestador.common.apis.ApiUser
import com.example.oprestador.common.base.DependecInjector
import com.example.oprestador.databinding.ActivityClienteBinding
import com.google.firebase.auth.FirebaseAuth

class ClienteActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration : AppBarConfiguration
    private lateinit var binding: ActivityClienteBinding
    private lateinit var navControler: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityClienteBinding.inflate(layoutInflater)

        with(binding) {

            setContentView(root)

            setSupportActionBar(appBarCliente.toolbarCliente)

            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_fragmentPedidoNovo, R.id.nav_fragmentPedidosAbertos
                ),drawerLayoutCliente
            )
        }

        navControler = findNavController(R.id.nav_cliente_fragment)

        setupActionBarWithNavController(navControler,appBarConfiguration)
        binding.navViewCliente.setupWithNavController(navControler)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navControler.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}