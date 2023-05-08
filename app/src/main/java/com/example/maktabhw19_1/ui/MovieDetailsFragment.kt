package com.example.maktabhw19_1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.maktabhw19_1.R
import com.example.maktabhw19_1.databinding.FragmentDetailsMoviesBinding
import com.example.maktabhw19_1.local.MovieEntity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private var _binding:FragmentDetailsMoviesBinding?=null
    private val binding get() = _binding!!
    private val viewModel:MovieDetailViewModel by viewModels()
    private val args:MovieDetailsFragmentArgs by navArgs()
    @Inject
    lateinit var movieEntity: MovieEntity
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentDetailsMoviesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            txtTitleDetail.text=args.popularmovie.title
            txtDateDetail.text=args.popularmovie.releaseDate
            txtOverviewDetail.text=args.popularmovie.overview
        }
        binding.btnSaveMovie.setOnClickListener {
            movieEntity.title=args.popularmovie.title.toString()
            movieEntity.posterPath=args.popularmovie.posterPath.toString()
            movieEntity.releaseDate=args.popularmovie.releaseDate.toString()
            viewModel.insertMovieEntity(movieEntity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}