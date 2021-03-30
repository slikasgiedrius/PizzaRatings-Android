package com.example.pizzaratings.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pizzaratings.R
import com.example.pizzaratings.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

  private lateinit var homeViewModel: HomeViewModel

  private var _binding: FragmentHomeBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root

    return root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    handleObservers()
    setUpViews()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun handleObservers() {
    homeViewModel.users.observe(viewLifecycleOwner, {
      binding.textHome.text = it.toString()
    })
  }

  private fun setUpViews() {
    binding.button.setOnClickListener {
      homeViewModel.fetchUsers()
    }
  }
}