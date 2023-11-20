package com.sterdevs.mybills.ui.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.sterdevs.mybills.R
import com.sterdevs.mybills.databinding.ActivityAuthBinding
import com.sterdevs.mybills.ui.views.fragments.LoginFragment
import com.sterdevs.mybills.ui.views.fragments.RegisterFragment

class AuthActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAuthBinding
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initializing the fragment manager
        fragmentManager = supportFragmentManager

        // The initial fragment is the login fragment

    }

    private fun goToFragment(fragment: Fragment)
    {
        fragmentManager.beginTransaction()
            .replace(binding.activityAuthFragmentContainer.id, fragment)
            .commit()
    }
}