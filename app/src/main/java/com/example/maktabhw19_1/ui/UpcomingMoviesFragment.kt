package com.example.maktabhw19_1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maktabhw19_1.adapter.ComingMoviesAdapter
import com.example.maktabhw19_1.common.ApiState
import com.example.maktabhw19_1.databinding.FragmentUpcomingMoviesBinding
import com.example.maktabhw19_1.model.commingmovies.ComingSoonResult
import com.example.maktabhw19_1.utils.NetworkConnection
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpcomingMoviesFragment : Fragment() {
    private var _binding:FragmentUpcomingMoviesBinding?=null
    private val binding get() = _binding!!
    private val viewModel:ComingMovieViewModel by viewModels()
    private lateinit var comingMoviesAdapter:ComingMoviesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentUpcomingMoviesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        comingMoviesAdapter=ComingMoviesAdapter(::onItemClicked)
        getComingMovies()
        binding.rvUpcoming.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=comingMoviesAdapter
        }


    }

    private fun getComingMovies(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                if (NetworkConnection.connected(requireContext())){
                    viewModel.data.collect{
                        when(it){
                            is ApiState.Success->{
                                binding.progress.isVisible=false
                                comingMoviesAdapter.differ.submitList(it.data.results?.toList())
                            }
                            is ApiState.Error->{
                                binding.progress.isVisible=false
                            }
                            ApiState.Loading->{
                                binding.progress.isVisible=true
                            }
                        }
                    }
                }

            }
        }
    }

    private fun onItemClicked(result: ComingSoonResult){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}