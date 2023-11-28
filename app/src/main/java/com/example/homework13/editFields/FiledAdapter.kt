package com.example.homework13.editFields

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework13.databinding.FieldItemBinding
import java.io.InputStream


class FiledAdapter: ListAdapter<Field, FiledAdapter.FiledVH>(object :

    DiffUtil.ItemCallback<Field>() {
    override fun areItemsTheSame(oldItem: Field, newItem: Field): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Field, newItem: Field): Boolean {
        return oldItem == newItem
    }
})

{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FiledVH(FieldItemBinding.inflate(
        LayoutInflater.from(parent.context), parent,false))

    override fun onBindViewHolder(holder: FiledVH, position: Int) {
        holder.bind()
    }

    inner class FiledVH(private val binding: FieldItemBinding): RecyclerView.ViewHolder(binding.root){
        private lateinit var field: Field
        fun bind(){
            field = currentList[adapterPosition]
            with(binding){
                edField.hint = field.hint

            }
        }


    }



}