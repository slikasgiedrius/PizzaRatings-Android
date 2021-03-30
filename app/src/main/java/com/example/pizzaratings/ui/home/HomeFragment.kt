package com.example.pizzaratings.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pizzaratings.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

  private lateinit var viewModel: HomeViewModel

  private var _binding: FragmentHomeBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

    handleObservers()
    setUpViews()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun handleObservers() {
    viewModel.users.observe(viewLifecycleOwner, {
      binding.textHome.text = it.toString()
    })
  }

  private fun setUpViews() {
    binding.button.setOnClickListener {
      viewModel.fetchUsers()
    }
  }
}