package com.giedrius.slikas.pizzaratings.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.giedrius.slikas.pizzaratings.compose.base.PizzaRatingsTheme
import com.giedrius.slikas.pizzaratings.ui.login.LoginActivity
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
      setContent {
        PizzaRatingsTheme {
          Column {
            Text("Profile Fragment")
            Button(onClick = {
              val intent = Intent(context, LoginActivity::class.java)
              startActivity(intent)
            }) {
              Text("Open Login Activity")
            }
          }
        }
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(requireActivity()).get(ProfileFragmentViewModel::class.java)
  }
}