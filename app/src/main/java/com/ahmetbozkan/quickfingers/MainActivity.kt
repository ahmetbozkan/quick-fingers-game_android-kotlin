package com.ahmetbozkan.quickfingers

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEachIndexed
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ahmetbozkan.quickfingers.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val viewModel: BaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupBottomNavigation()

        viewModel.invokeDatabaseCallback()
    }

    private fun setupToolbar() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.findNavController()
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.scoreboardFragment, R.id.startGameFragment, R.id.settingsFragment)
        )

        binding.apply {
            setSupportActionBar(toolbar)
            setupActionBarWithNavController(navController, appBarConfiguration)
        }
    }

    private fun setupBottomNavigation() {
        binding.apply {
            bottomNavigation.apply {
                selectedItemId = R.id.invisible
                setupWithNavController(navController)
                background = null
                setOnItemReselectedListener {}
            }

            fabPlay.setOnClickListener {
                if (bottomNavigation.selectedItemId != R.id.startGameFragment) {
                    val action = R.id.action_global_startGameFragmet
                    navController.navigate(action)
                }

                bottomNavigation.menu.forEachIndexed { index, _ ->
                    bottomNavigation.menu[index].isChecked = false
                }
            }

            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id != R.id.scoreboardFragment && destination.id != R.id.startGameFragment &&
                    destination.id != R.id.settingsFragment
                ) {
                    bottomNavigation.isVisible = false
                    fabPlay.isVisible = false
                    bottomAppBar.isVisible = false
                } else {
                    bottomNavigation.isVisible = true
                    bottomAppBar.isVisible = true
                    fabPlay.isVisible = true
                }

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}