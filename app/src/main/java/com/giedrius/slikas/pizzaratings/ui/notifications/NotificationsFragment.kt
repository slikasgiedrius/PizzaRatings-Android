package com.giedrius.slikas.pizzaratings.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.giedrius.slikas.pizzaratings.compose.ComposeActivity
import com.giedrius.slikas.pizzaratings.databinding.FragmentNotificationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : Fragment() {

  private lateinit var viewModel: NotificationsFragmentViewModel

  private var _binding: FragmentNotificationsBinding? = null
  private val binding get() = _binding!!

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(requireActivity()).get(NotificationsFragmentViewModel::class.java)

    handleObservers()
    setUpViews()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun handleObservers() {
    viewModel.text.observe(viewLifecycleOwner, Observer {
      binding.textNotifications.text = it
    })
  }

  private fun setUpViews() {
    binding.compose.setOnClickListener { navigateToComposeActivity() }
  }

  private fun navigateToComposeActivity() {
    val intent = Intent(context, ComposeActivity::class.java)
    startActivity(intent)
  }
}