package com.example.oprestador.lnicial.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.oprestador.R
import com.example.oprestador.databinding.ActivityInicialBinding


class InicialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInicialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInicialBinding.inflate(layoutInflater)

        installSplashScreen()

        setContentView(binding.root)

        val navInicialController = supportFragmentManager.findFragmentById(R.id.nav_inicial_fragment) as NavHostFragment
        val navController : NavController = navInicialController.navController
//        NavigationUI.setupActionBarWithNavController(this, navController)
    }
}