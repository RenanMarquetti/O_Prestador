package com.example.oprestador.user.view



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

class UserActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_user)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.nav_view)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_fragmentListaPedidos, R.id.nav_fragmentMeusPedidos
                , R.id.nav_fragmentPerfil
            ),drawerLayout
        )

        val navControler = findNavController(R.id.nav_user_fragment)

        setupActionBarWithNavController(navControler,appBarConfiguration)
        navView.setupWithNavController(navControler)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navControler = findNavController(R.id.nav_user_fragment)
        return navControler.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}
