package com.giedrius.slikas.pizzaratings

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.giedrius.slikas.pizzaratings.databinding.ActivityMainBinding
import com.giedrius.slikas.pizzaratings.ui.home.HomeFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private val viewModel: MainActivityViewModel by viewModels()
  private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()

  private lateinit var binding: ActivityMainBinding
  private lateinit var navView: BottomNavigationView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    ViewTreeLifecycleOwner.set(window.decorView, this)

    initBottomNavigation()
    downloadPizzerias()
  }

  private fun initBottomNavigation() {
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    navView = binding.navView

    val navController = findNavController(R.id.nav_host_fragment_activity_main)
    val appBarConfiguration = AppBarConfiguration(
      setOf(
        R.id.homeFragment, R.id.favouritesFragment, R.id.profileFragment
      )
    )
    setupActionBarWithNavController(navController, appBarConfiguration)
    navView.setupWithNavController(navController)
  }

  private fun downloadPizzerias() = homeFragmentViewModel.pizzaRepository.getPizzeriasList()
}