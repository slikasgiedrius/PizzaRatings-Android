package com.giedrius.slikas.pizzaratings.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.giedrius.slikas.pizzaratings.compose.base.PizzaRatingsTheme
import com.giedrius.slikas.pizzaratings.utils.extensions.toast
import com.giedrius.slikas.pizzaratings.utils.randomInt
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
          HomeFragmentContent(
            viewModel,
            ::onItemClicked
          )
        }
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(requireActivity()).get(HomeFragmentViewModel::class.java)

    handleObservers()
  }

  private fun handleObservers() {
    viewModel.onUserNotFound.observe(viewLifecycleOwner) {
      userIdentificationProblem()
    }
  }

  private fun onItemClicked(pizzeriaName: String){
    navigateToDetailsFragment(pizzeriaName)
//    viewModel.saveRating(
//      pizzeriaName,
//      randomInt()
//    )
  }

  private fun userIdentificationProblem() = requireActivity().toast("User identification problem!")

  private fun navigateToDetailsFragment(pizzeriaName: String) {
    val action = HomeFragmentDirections.actionNavigationHomeToDetailsFragment(pizzeriaName)
    view?.findNavController()?.navigate(action)
  }
}