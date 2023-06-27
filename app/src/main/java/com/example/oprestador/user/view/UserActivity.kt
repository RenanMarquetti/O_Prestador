package com.example.oprestador.user.view



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.oprestador.R
import com.example.oprestador.common.model.Database
import com.example.oprestador.databinding.ActivityUserBinding
import com.google.android.material.navigation.NavigationView

class UserActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration : AppBarConfiguration
    private lateinit var binding: ActivityUserBinding
    private lateinit var navControler: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)

        with(binding) {

            setContentView(root)

            setSupportActionBar(appBarMain.toolbar)

            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_fragmentListaPedidos, R.id.nav_fragmentMeusPedidos
                    , R.id.nav_fragmentPerfil
                ),drawerLayoutUser
            )

            navControler = findNavController(R.id.nav_user_fragment)

            setupActionBarWithNavController(navControler,appBarConfiguration)
            navViewUser.setupWithNavController(navControler)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navControler.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}
