package com.ahmetbozkan.quickfingers

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEachIndexed
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ahmetbozkan.quickfingers.databinding.ActivityMainBinding
import com.ahmetbozkan.quickfingers.util.extension.goneView
import com.ahmetbozkan.quickfingers.util.extension.hideMenuItem
import com.ahmetbozkan.quickfingers.util.extension.showView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val viewModel: MainViewModel by viewModels()

    // starting destination is startGameFragment by default
    private var destinationId: Int = R.id.startGameFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()

        setupBottomNavigation()

        manageClick()

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

            navController.addOnDestinationChangedListener { _, destination, _ ->
                destinationId = destination.id

                if (areTopLevelScreensDisplayed(destinationId))
                    showBottomViews()
                 else
                    hideBottomViews()
            }

        }
    }

    private fun manageClick() {
        binding.apply {
            fabPlay.setOnClickListener {
                if (bottomNavigation.selectedItemId != R.id.startGameFragment) {
                    val action = NavGraphDirections.actionGlobalStartGameFragmet()
                    navController.navigate(action)
                }

                bottomNavigation.menu.forEachIndexed { index, _ ->
                    bottomNavigation.menu[index].isChecked = false
                }
            }
        }
    }

    private fun areTopLevelScreensDisplayed(destinationId: Int): Boolean =
        destinationId == R.id.scoreboardFragment || destinationId == R.id.startGameFragment ||
                destinationId == R.id.settingsFragment

    private fun hideBottomViews() {
        binding.apply {
            fabPlay.goneView()
            bottomAppBar.goneView()
            bottomNavigation.goneView()
        }
    }

    private fun showBottomViews() {
        binding.apply {
            fabPlay.showView()
            bottomAppBar.showView()
            bottomNavigation.showView()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)

        if(!areTopLevelScreensDisplayed(destinationId))
            menu?.hideMenuItem(R.id.howToPlayFragment)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}