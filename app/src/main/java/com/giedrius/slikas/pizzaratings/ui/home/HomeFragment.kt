package com.giedrius.slikas.pizzaratings.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.giedrius.slikas.pizzaratings.compose.base.PizzaRatingsTheme
import com.giedrius.slikas.pizzaratings.data.model.User
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
          HomeFragmentContent(viewModel)
        }
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(requireActivity()).get(HomeFragmentViewModel::class.java)
  }
}

@Composable
fun HomeFragmentContent(viewModel: HomeFragmentViewModel) {
  val user = viewModel.user.observeAsState().value

  Column {
    Text(text = "Home Fragment $user")
    Button(
      onClick = { viewModel.fetchUsers() }
    ) {
      Text("Launch API call")
    }
  }
}