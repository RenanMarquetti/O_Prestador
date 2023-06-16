package com.example.oprestador.cliente.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.oprestador.R
import com.google.android.material.navigation.NavigationView

class ClienteActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cliente)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_cliente)
        setSupportActionBar(toolbar)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout_cliente)
        val navView = findViewById<NavigationView>(R.id.nav_view_cliente)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_fragmentPedidoNovo, R.id.nav_fragmentPedidosAbertos
            ),drawerLayout
        )

        val navControler = findNavController(R.id.nav_cliente_fragment)

        setupActionBarWithNavController(navControler,appBarConfiguration)
        navView.setupWithNavController(navControler)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navControler = findNavController(R.id.nav_cliente_fragment)
        return navControler.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}