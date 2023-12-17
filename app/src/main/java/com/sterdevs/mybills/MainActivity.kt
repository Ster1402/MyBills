package com.sterdevs.mybills

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.sterdevs.mybills.databinding.ActivityMainBinding
import com.sterdevs.mybills.features.bills.ui.views.HistoryFragment
import com.sterdevs.mybills.features.news.ui.views.fragments.NewsFragment
import com.sterdevs.mybills.features.settings.ui.views.fragments.SettingsFragment
import com.sterdevs.mybills.features.wallet.ui.views.fragments.WalletFragment
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.sterdevs.mybills.core.ui.states.AppGlobalState
import com.sterdevs.mybills.core.ui.states.AppGlobalStateObserver
import com.sterdevs.mybills.core.ui.utils.ScreenUtils
import com.sterdevs.mybills.features.authentication.ui.views.activities.AuthenticationActivity
import com.sterdevs.mybills.features.home.ui.views.fragments.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ScreenUtils, AppGlobalStateObserver {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pageTitle: TextView
    private lateinit var toolbarLogoButton: ImageView
    private lateinit var usernameTextView: TextView
    private lateinit var navigationDrawerView: NavigationView
    private lateinit var headerDrawerView: View
    private lateinit var drawerUserFullName: TextView
    private lateinit var drawerUsername: TextView

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
        replaceFragment(HomeFragment())
        // Add listeners on views
        addViewsEventsListeners()
        // Add observables
        subscribeToObservables()
        //Add Badges
        setupBadge(bottomNavigationView, R.id.bottom_newspaper, true, 5)
        setupBadge(bottomNavigationView, R.id.bottom_home, true, 3)
    }

    private fun openDrawer() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun setupBadge(
        bottomNavigationView: BottomNavigationView,
        itemId: Int,
        isVisible: Boolean = false,
        number: Int = 0
    ) {
        val badge = bottomNavigationView.getOrCreateBadge(itemId)
        badge.isVisible = isVisible
        badge.number = number
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
        navigationDrawerView = binding.navigationView
        headerDrawerView = navigationDrawerView.getHeaderView(0)
        drawerUserFullName = headerDrawerView.findViewById(R.id.drawer_user_fullname)
        drawerUsername = headerDrawerView.findViewById(R.id.drawer_username)
        toolbarLogoButton = binding.logo
        bottomNavigationView = binding.bottomNavigation
    }

    override fun initializeDefaultValues() {
        usernameTextView.text = AppGlobalState.userState.value?.username ?: "Unknown"
        drawerUserFullName.text = AppGlobalState.userState.value?.name ?: "Unknown"
        drawerUsername.text = AppGlobalState.userState.value?.username ?: "Unknown"
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

        // Drawer navigation
    }

    override fun subscribeToObservables() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                observeUserStateChanged()
            }
        }
    }

    override suspend fun observeUserStateChanged() {
        AppGlobalState.userState.collect {
            it?.let {
                initializeDefaultValues()
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