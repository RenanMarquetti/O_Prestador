package com.example.oprestador.user.view



import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

    override fun goToPedidoDetalhadoScrean(pedido: Pedido) {

        val fragment = FragmentPedidoDetalhado()
        fragment.setPedido(pedido)
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment) {

        val isStackEmpty = supportFragmentManager.findFragmentById(R.id.nav_user_fragment) == null

        supportFragmentManager.beginTransaction().apply {
            if (isStackEmpty) add(R.id.nav_user_fragment, fragment)
            else {
                replace(R.id.nav_user_fragment, fragment)
                addToBackStack(null)
            }
            commit()
        }
    }
}
