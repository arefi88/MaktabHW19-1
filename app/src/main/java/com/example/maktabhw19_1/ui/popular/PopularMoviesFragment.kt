package com.example.maktabhw19_1.ui.popular

import android.os.Bundle
import android.util.Log
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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maktabhw19_1.EndlessRecyclerOnScrollListener
import com.example.maktabhw19_1.adapter.PopularMovieAdapter
import com.example.maktabhw19_1.common.ApiState
import com.example.maktabhw19_1.databinding.FragmentPopularMoviesBinding
import com.example.maktabhw19_1.model.popularmovies.ResultPopular
import com.example.maktabhw19_1.utils.API_KEY
import com.example.maktabhw19_1.utils.NetworkConnection
import com.example.maktabhw19_1.utils.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {
    private var _binding: FragmentPopularMoviesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PopularMovieViewModel by viewModels()
    private lateinit var popularMovieAdapter: PopularMovieAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    private var pastVisiblesItems = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularMovieAdapter = PopularMovieAdapter(::onItemClicked)

        mLayoutManager = LinearLayoutManager(requireContext())
        binding.rvPopular.apply {
            layoutManager = mLayoutManager
            adapter = popularMovieAdapter
        }
        setupPagination()
        getPopularMovies(viewModel.page)
        viewModel.popularMoviesLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                popularMovieAdapter.submitList(it.results?.toList())

            }

        }


    }


    private fun onItemClicked(result: ResultPopular) {
        val action =
            PopularMoviesFragmentDirections.actionPopularMoviesFragmentToMovieDetailsFragment(result)

        findNavController().navigate(action)
    }

    private fun setupPagination() {
        binding.rvPopular.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = mLayoutManager.childCount
                totalItemCount = mLayoutManager.itemCount
                pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition()
               // if (!viewModel.isLoading) {
                    if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                        viewModel.page++
                        getPopularMovies(viewModel.page)

                    }
                //}


            }
        })
    }

    private fun getPopularMovies(page: Int) {
       // viewModel.isLoading = true
        lifecycleScope.launch {
           // repeatOnLifecycle(Lifecycle.State.STARTED) {
                if (NetworkConnection.connected(requireContext())) {
                    viewModel.getAllPopularMovies(binding.progress, API_KEY, page)
                }

           // }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
