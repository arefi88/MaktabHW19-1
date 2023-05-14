package com.example.maktabhw19_1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.maktabhw19_1.databinding.ItemComingMoviesBinding
import com.example.maktabhw19_1.model.commingmovies.ComingSoonResult
import javax.inject.Inject

class ComingMoviesAdapter @Inject constructor(private val onItemClicked:(ComingSoonResult)->Unit): RecyclerView.Adapter<ComingMoviesAdapter.ComingMoviesViewHolder>() {

    inner class ComingMoviesViewHolder(private  var binding: ItemComingMoviesBinding): RecyclerView.ViewHolder(binding.root){
        fun setData(result: ComingSoonResult){

            binding.txtTitleComing.text=result.title
            binding.txtDateComing.text=result.releaseDate
            binding.imgMovieComing.load(result.posterPath){
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            binding.root.setOnClickListener {
                onItemClicked(result)
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComingMoviesViewHolder {
       val binding= ItemComingMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ComingMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComingMoviesViewHolder, position: Int) {
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