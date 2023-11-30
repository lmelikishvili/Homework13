package com.example.homework13.editFields

import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework13.R
import com.example.homework13.databinding.FieldItemBinding

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
            when(field.id){
                1 -> binding.edField.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_account, 0)
                2 -> binding.edField.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_email, 0)
                3 -> binding.edField.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_phone, 0)
                4 -> binding.edField.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_account, 0)
                14 -> binding.edField.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_account, 0)
                89 -> binding.edField.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_cake, 0)
                898 -> binding.edField.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_transgender, 0)
            }
            if (field.id == 3)
                binding.edField.inputType = InputType.TYPE_CLASS_NUMBER
            with(binding){
                edField.hint = field.hint
            }

        }

        //need Fix this
        fun emptyCheck(){
            var item = currentList[adapterPosition]

        }

    }
}