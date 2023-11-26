    package com.sterdevs.mybills.ui.views.activities

    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import androidx.core.view.GravityCompat
    import androidx.fragment.app.Fragment
    import com.sterdevs.mybills.R
    import com.sterdevs.mybills.databinding.ActivityMainBinding
    import com.sterdevs.mybills.ui.views.fragments.HistoryFragment
    import com.sterdevs.mybills.ui.views.fragments.NewspaperFragment
    import com.sterdevs.mybills.ui.views.fragments.SettingsFragment
    import com.sterdevs.mybills.ui.views.fragments.WalletFragment
    import android.content.Intent
    class MainActivity : AppCompatActivity() {
        private lateinit var binding : ActivityMainBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            setupDrawerLayout()
           // replaceActivity(HomeActivity::class.java)
            // Accéder à BottomNavigationView
            val bottomNavigationView = binding.bottomNavigation
            bottomNavigationView.setOnItemSelectedListener { menuItem ->
                when(menuItem.itemId){
                    R.id.bottom_home ->{
                        replaceActivity(HomeActivity::class.java)
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
        private fun setupDrawerLayout() {
            binding.logo.setOnClickListener {
                openDrawer()
            }
        }
        private fun openDrawer() {
            // Ouvrez le tiroir lorsque l'ImageView est cliqué
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        private fun replaceFragment(fragment: Fragment) {
            supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
        }
        private fun replaceActivity(destinationActivity: Class<*>) {
            val intent = Intent(this, destinationActivity)
            startActivity(intent)
        }
    }