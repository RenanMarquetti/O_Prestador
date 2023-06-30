package com.example.oprestador.user.view



import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.oprestador.R
import com.example.oprestador.common.model.Pedido
import com.example.oprestador.databinding.ActivityUserBinding
import com.example.oprestador.user.FragmentAttachListener

class UserActivity : AppCompatActivity(), FragmentAttachListener {

    private lateinit var appBarConfiguration : AppBarConfiguration
    private lateinit var binding: ActivityUserBinding
    private lateinit var navControler: NavController
    private lateinit var pedido: Pedido

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

    override fun goToPedido(pedido: Pedido) {
        this.pedido = pedido
    }
}
