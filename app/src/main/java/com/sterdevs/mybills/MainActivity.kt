    package com.sterdevs.mybills

    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import androidx.core.view.GravityCompat
    import androidx.fragment.app.Fragment
    import com.sterdevs.mybills.databinding.ActivityMainBinding
    import com.sterdevs.mybills.features.home.ui.fragments.HistoryFragment
    import com.sterdevs.mybills.features.home.ui.fragments.NewspaperFragment
    import com.sterdevs.mybills.features.home.ui.fragments.SettingsFragment
    import com.sterdevs.mybills.features.wallet.ui.views.fragments.WalletFragment
    import android.content.Intent
    import android.util.Log
    import com.sterdevs.mybills.features.home.ui.views.fragments.HomeFragment

    class MainActivity : AppCompatActivity() {
        private lateinit var binding : ActivityMainBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            replaceFragment(HomeFragment())
            openDrawer()
            // Accéder à BottomNavigationView
            val bottomNavigationView = binding.bottomNavigation
            bottomNavigationView.setOnItemSelectedListener { menuItem ->
                when(menuItem.itemId){
                    R.id.bottom_home ->{
                        replaceFragment(HomeFragment())
                        true
                    }
                    R.id.bottom_settings -> {
                        replaceFragment(SettingsFragment())
                        true
                    } R.id.bottom_newspaper -> {
                    replaceFragment(NewspaperFragment())
                    true
                } R.id.bottom_wallet -> {
                    replaceFragment(WalletFragment())
                    true
                } R.id.bottom_history -> {
                    replaceFragment(HistoryFragment())
                    true
                }
                    else -> {false}
                }
            }
        }
        private fun openDrawer() {
            binding.logo.setOnClickListener {
                binding.drawerLayout.openDrawer(GravityCompat.START)
                Log.d("MainActivity", "Drawer opened")
            }
        }
        private fun replaceFragment(fragment: Fragment) {
            supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
        }

    }