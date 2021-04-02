package com.giedrius.slikas.pizzaratings.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.giedrius.slikas.pizzaratings.compose.ui.theme.PizzaRatingsTheme
import com.giedrius.slikas.pizzaratings.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

  private lateinit var viewModel: HomeFragmentViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        PizzaRatingsTheme {
          Text(text = "Home Fragment")
        }
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(requireActivity()).get(HomeFragmentViewModel::class.java)

    handleObservers()
    setUpViews()
  }


  private fun handleObservers() {
    viewModel.users.observe(viewLifecycleOwner, {
//      binding.textHome.text = it.toString()
    })
  }

  private fun setUpViews() {
//    binding.button.setOnClickListener {
//      viewModel.fetchUsers()
//    }
  }
}