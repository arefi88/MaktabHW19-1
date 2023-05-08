package com.example.maktabhw19_1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maktabhw19_1.adapter.SelectedMoviesAdapter
import com.example.maktabhw19_1.databinding.FragmentSelectedMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectedMoviesFragment : Fragment() {
    private var _binding:FragmentSelectedMoviesBinding?=null
    private val binding get() =_binding!!
    private val viewModel:SelectedMoviesViewModel by viewModels()
    private lateinit var selectedMoviesAdapter: SelectedMoviesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentSelectedMoviesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedMovieLiveData.observe(viewLifecycleOwner){
            selectedMoviesAdapter.differ.submitList(it.toList())
        }
        selectedMoviesAdapter= SelectedMoviesAdapter()
        binding.rvSelected.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=selectedMoviesAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}