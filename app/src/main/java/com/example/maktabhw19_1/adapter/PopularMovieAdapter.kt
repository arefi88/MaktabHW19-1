package com.example.maktabhw19_1.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.transform.CircleCropTransformation
import com.example.maktabhw19_1.databinding.ItemPopularMovieBinding
import com.example.maktabhw19_1.model.popularmovies.ResultPopular
import com.example.maktabhw19_1.utils.TAG
import javax.inject.Inject




class PopularMovieAdapter(private val onItemClicked:(ResultPopular)->Unit): ListAdapter<ResultPopular,PopularMovieAdapter.PopularMovieViewHolder>(object : DiffUtil.ItemCallback<ResultPopular>(){
    override fun areItemsTheSame(oldItem: ResultPopular, newItem: ResultPopular): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: ResultPopular, newItem: ResultPopular): Boolean {
        return oldItem==newItem
    }

}) {


    inner class PopularMovieViewHolder(private var binding:ItemPopularMovieBinding): ViewHolder(binding.root){

        fun setData(result:ResultPopular){

            binding.txtTitle.text=result.title
            binding.txtDate.text=result.releaseDate
            binding.imgMovie.load("https://image.tmdb.org/t/p/w200${result.posterPath}"){
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            binding.root.setOnClickListener {
                onItemClicked(result)
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
       val binding=ItemPopularMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PopularMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        holder.setData(getItem(position))
        Log.e(TAG,"onCreate: ")

    }





}