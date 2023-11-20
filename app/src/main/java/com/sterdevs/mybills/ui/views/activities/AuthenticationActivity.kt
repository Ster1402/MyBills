package com.sterdevs.mybills.ui.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.sterdevs.mybills.R
import com.sterdevs.mybills.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var navController : NavController
    private lateinit var binding : ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Show splashscreen
        Thread.sleep(3000)
        installSplashScreen()

        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.activity_auth_fragment_container)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}