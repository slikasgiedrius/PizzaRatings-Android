package com.giedrius.slikas.pizzaratings.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.giedrius.slikas.pizzaratings.compose.ui.theme.PizzaRatingsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : Fragment() {

  private lateinit var viewModel: FavouritesFragmentViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        PizzaRatingsTheme {
          Text(text = "Favourites Fragment")
        }
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(requireActivity()).get(FavouritesFragmentViewModel::class.java)

    handleObservers()
    setUpViews()
  }

  private fun handleObservers() {
    viewModel.text.observe(viewLifecycleOwner) {
//      binding.textDashboard.text = it
    }
    viewModel.userRepository.onUsersDownloaded.observe(viewLifecycleOwner) {
//      binding.textDashboard.text = it.toString()
    }
  }

  private fun setUpViews() {
//    binding.get.setOnClickListener { viewModel.userRepository.getUsers() }
//    binding.save.setOnClickListener { viewModel.userRepository.saveUser() }
  }
}