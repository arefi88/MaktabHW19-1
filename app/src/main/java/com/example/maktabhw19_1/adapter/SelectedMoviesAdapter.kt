package com.example.maktabhw19_1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.maktabhw19_1.databinding.ItemSelectedMoviesBinding
import com.example.maktabhw19_1.local.entity.MovieEntity
import javax.inject.Inject

class SelectedMoviesAdapter @Inject constructor(): RecyclerView.Adapter<SelectedMoviesAdapter.SelectedMoviesViewHolder>() {

    inner class SelectedMoviesViewHolder(private var binding: ItemSelectedMoviesBinding): RecyclerView.ViewHolder(binding.root){
        fun setData(movieEntity: MovieEntity){

            binding.txtTitleSelected.text=movieEntity.title
            binding.txtDateSelected.text=movieEntity.releaseDate
            binding.imgMovieSelected.load(movieEntity.posterPath){
                crossfade(true)
                transformations(CircleCropTransformation())
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedMoviesViewHolder {
       val binding= ItemSelectedMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SelectedMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectedMoviesViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(true)



    }

    override fun getItemCount() = differ.currentList.size


    private val differCallback= object : DiffUtil.ItemCallback<MovieEntity>(){
        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem==newItem
        }

    }
    val differ= AsyncListDiffer(this,differCallback)
}