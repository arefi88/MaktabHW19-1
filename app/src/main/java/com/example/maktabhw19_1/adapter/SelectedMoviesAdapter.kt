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

class SelectedMoviesAdapter @Inject constructor(): RecyclerView.Adapter<SelectedMoviesAdapter.ViewHolder>() {
    private lateinit var binding: ItemSelectedMoviesBinding
    inner class ViewHolder: RecyclerView.ViewHolder(binding.root){
        fun setData(movieEntity: MovieEntity){

            binding.txtTitleSelected.text=movieEntity.title
            binding.txtDateSelected.text=movieEntity.releaseDate
            binding.imgMovieSelected.load(movieEntity.posterPath){
                crossfade(true)
                transformations(CircleCropTransformation())
            }
//            binding.root.setOnClickListener {
//                onItemClicked(movieEntity)
//            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= ItemSelectedMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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