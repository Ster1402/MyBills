package com.sterdevs.mybills

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.sterdevs.mybills.databinding.ActivityMainBinding
import com.sterdevs.mybills.features.home.ui.fragments.HistoryFragment
import com.sterdevs.mybills.features.news.ui.views.fragments.NewsFragment
import com.sterdevs.mybills.features.home.ui.fragments.SettingsFragment
import com.sterdevs.mybills.features.wallet.ui.views.fragments.WalletFragment
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sterdevs.mybills.core.ui.states.AppGlobalState
import com.sterdevs.mybills.core.ui.states.AppGlobalStateObserver
import com.sterdevs.mybills.core.ui.utils.ScreenUtils
import com.sterdevs.mybills.features.authentication.ui.views.activities.AuthenticationActivity
import com.sterdevs.mybills.features.home.ui.views.fragments.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ScreenUtils, AppGlobalStateObserver {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pageTitle: TextView
    private lateinit var toolbarLogoButton: ImageView
    private lateinit var usernameTextView: TextView

    // Bottom navigation
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Gets binding views
        getViews()
        // Initialize defaults values if any
        initializeDefaultValues()
        // Add listeners on views
        addViewsEventsListeners()
    }

    private fun openDrawer() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.app_fragment_container, fragment)
            .commit()
        replaceToolbarTitle(fragment)
    }

    private fun replaceToolbarTitle(fragment: Fragment) {
        when (fragment) {
            is HomeFragment -> {
                pageTitle.text = getString(R.string.title_text_home)
            }

            is WalletFragment -> {
                pageTitle.text = getString(R.string.title_text_wallet)
            }

            is NewsFragment -> {
                pageTitle.text = getString(R.string.title_text_news)
            }

            is HistoryFragment -> {
                pageTitle.text = getString(R.string.title_text_history)
            }

            is SettingsFragment -> {
                pageTitle.text = getString(R.string.title_text_settings)
            }

            else -> pageTitle.text = getString(R.string.dummy_content)
        }
    }

    override fun getViews() {
        pageTitle = binding.activityMainTitle
        usernameTextView = binding.activityMainUsername
        toolbarLogoButton = binding.logo
        bottomNavigationView = binding.bottomNavigation
    }

    override fun initializeDefaultValues() {
        pageTitle.text = getString(R.string.title_text_home)
        usernameTextView.text = AppGlobalState.userState.value?.username ?: "Unknown"
        replaceFragment(HomeFragment())
    }

    override fun addViewsEventsListeners() {
        toolbarLogoButton.setOnClickListener {
            openDrawer()
        }
        // Accéder à BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.bottom_settings -> {
                    replaceFragment(SettingsFragment())
                    true
                }

                R.id.bottom_newspaper -> {
                    replaceFragment(NewsFragment())
                    true
                }

                R.id.bottom_wallet -> {
                    replaceFragment(WalletFragment())
                    true
                }

                R.id.bottom_history -> {
                    replaceFragment(HistoryFragment())
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    override fun onUserLoggedOut() {
        super.onUserLoggedOut()
        // Go to AuthActivity
        val intent = Intent(applicationContext, AuthenticationActivity::class.java)
        startActivity(intent)
        finish()
    }

}