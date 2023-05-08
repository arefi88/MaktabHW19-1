package com.example.maktabhw19_1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maktabhw19_1.EndlessRecyclerOnScrollListener
import com.example.maktabhw19_1.R
import com.example.maktabhw19_1.adapter.PopularMovieAdapter
import com.example.maktabhw19_1.common.ApiState
import com.example.maktabhw19_1.databinding.FragmentPopularMoviesBinding
import com.example.maktabhw19_1.model.popularmovies.ResultPopular
import com.example.maktabhw19_1.utils.NetworkConnection
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {
    private var _binding: FragmentPopularMoviesBinding?=null
    private val binding get() = _binding!!
    private val viewModel:PopularMovieViewModel by viewModels()
    private lateinit var popularMovieAdapter:PopularMovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentPopularMoviesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularMovieAdapter=PopularMovieAdapter(::onItemClicked)
           getPopularMovies()
        binding.rvPopular.layoutManager=LinearLayoutManager(requireContext())
           binding.rvPopular.adapter=popularMovieAdapter
            binding.rvPopular.addOnScrollListener(object : EndlessRecyclerOnScrollListener(
                 binding.rvPopular.layoutManager as LinearLayoutManager,
                20
             ){
                 override fun onLoadMore(currentPage: Int) {
                    // viewModel.getAllPopularMovies()
                     getPopularMovies()
                 }

             })

        }
    

    private fun onItemClicked(result:ResultPopular){
         val action=PopularMoviesFragmentDirections.actionPopularMoviesFragmentToMovieDetailsFragment(result)
         findNavController().navigate(action)
    }

   private fun getPopularMovies(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                if (NetworkConnection.connected(requireContext())){
                    viewModel.data.collect{
                        when(it){
                            is ApiState.Success->{
                                binding.apply {
                                    progress.isVisible=false
                                }
                                popularMovieAdapter.differ.submitList(it.data.results?.toList())
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}
