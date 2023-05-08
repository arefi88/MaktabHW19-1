package com.example.maktabhw19_1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.maktabhw19_1.databinding.ItemPopularMovieBinding
import com.example.maktabhw19_1.model.commingmovies.ComingSoonResult
import com.example.maktabhw19_1.model.popularmovies.ResultPopular
import javax.inject.Inject

class ComingMoviesAdapter @Inject constructor(private val onItemClicked:(ComingSoonResult)->Unit): RecyclerView.Adapter<ComingMoviesAdapter.ViewHolder>() {
    private lateinit var binding: ItemPopularMovieBinding
    inner class ViewHolder: RecyclerView.ViewHolder(binding.root){
        fun setData(result: ComingSoonResult){

            binding.txtTitle.text=result.title
            binding.txtDate.text=result.releaseDate
            binding.imgMovie.load(result.posterPath){
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            binding.root.setOnClickListener {
                onItemClicked(result)
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= ItemPopularMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(true)



    }

    override fun getItemCount() = differ.currentList.size


    private val differCallback= object : DiffUtil.ItemCallback<ComingSoonResult>(){
        override fun areItemsTheSame(oldItem: ComingSoonResult, newItem: ComingSoonResult): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ComingSoonResult, newItem: ComingSoonResult): Boolean {
            return oldItem==newItem
        }

    }
    val differ= AsyncListDiffer(this,differCallback)
}