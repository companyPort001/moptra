package com.example.myapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Domain.OngoingDomain
import com.example.myapplication.databinding.ViewHolderBinding

class OngoingAdapter(
    private val item: List<OngoingDomain>,
    private val onItemClick: (Int) -> Unit  // Pass the position to the callback
) : RecyclerView.Adapter<OngoingAdapter.Viewholder>() {

    inner class Viewholder(private val binding: ViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: OngoingDomain, position: Int) {
            binding.title.text = currentItem.title

            // Load image using Glide
            val drawableResourceId = binding.root.context.resources.getIdentifier(
                currentItem.picPath, "drawable", binding.root.context.packageName
            )
            Glide.with(binding.root.context).load(drawableResourceId).into(binding.imageViewholder)

            // Handle item click with position
            binding.root.setOnClickListener {
                onItemClick(position)  // Pass the position on click
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = ViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind(item[position], position)  // Pass the position while binding
    }
}
