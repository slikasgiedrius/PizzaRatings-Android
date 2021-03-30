package com.giedrius.slikas.pizzaratings.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.giedrius.slikas.pizzaratings.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

  private lateinit var viewModel: DashboardFragmentViewModel

  private var _binding: FragmentDashboardBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentDashboardBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(requireActivity()).get(DashboardFragmentViewModel::class.java)

    handleObservers()
    setUpViews()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun handleObservers() {
    viewModel.text.observe(viewLifecycleOwner) {
      binding.textDashboard.text = it
    }
    viewModel.userRepository.onUsersDownloaded.observe(viewLifecycleOwner) {
      binding.textDashboard.text = it.toString()
    }
  }

  private fun setUpViews() {
    binding.get.setOnClickListener { viewModel.userRepository.getUsers() }
    binding.save.setOnClickListener { viewModel.userRepository.saveUser() }
  }
}