package com.giedrius.slikas.pizzaratings.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.giedrius.slikas.pizzaratings.compose.NewsStory
import com.giedrius.slikas.pizzaratings.compose.base.PizzaRatingsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

  private lateinit var viewModel: ProfileFragmentViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      //Set compose here
      setContent {
        PizzaRatingsTheme {
          NewsStory()
        }
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(requireActivity()).get(ProfileFragmentViewModel::class.java)

    handleObservers()
  }

  private fun handleObservers() {
    viewModel.text.observe(viewLifecycleOwner, Observer {
//      binding.textNotifications.text = it
    })
  }
}