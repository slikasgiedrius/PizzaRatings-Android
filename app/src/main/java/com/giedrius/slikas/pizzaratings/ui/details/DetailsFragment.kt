package com.giedrius.slikas.pizzaratings.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.navArgs
import com.giedrius.slikas.pizzaratings.R
import com.giedrius.slikas.pizzaratings.compose.base.PizzaRatingsTheme
import com.giedrius.slikas.pizzaratings.ui.favourites.FavouritesFragmentViewModel
import com.giedrius.slikas.pizzaratings.utils.extensions.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

  private lateinit var viewModel: DetailsFragmentViewModel
  private val args: DetailsFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        PizzaRatingsTheme {
          DetailsFragmentContent(
            viewModel
          )
        }
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(requireActivity()).get(DetailsFragmentViewModel::class.java)
    handleObservers()

    viewModel.loadPizzeriaDetails(args.name)
  }

  private fun handleObservers() {
    viewModel.onUserNotFound.observe(viewLifecycleOwner) {
      userIdentificationProblem()
    }
  }

  private fun userIdentificationProblem() = requireActivity().toast("User identification problem!")
}