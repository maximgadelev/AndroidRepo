package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemTodoBinding
import com.example.myapplication.entity.Todo

class TodoAdapter(private val todoList: ArrayList<Todo>):
    ListAdapter<Todo, TodoAdapter.TodoViewHolder>(DiffCallback()) {
    var infoClickListener: ((todo: Todo) -> Unit)? = null
    var deleteClickListener: ((todo: Todo) -> Unit)? = null

    inner class TodoViewHolder(
        private val binding: ItemTodoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Todo) = with(binding) {
            tvTitle.text = if (item.title!=null) item.title else "Укажите Название!"
            tvAbout.text=if(item.description!=null) item.description else "Укажите информацию!"
            tvDate.text=if(item.date.toString()!=null) item.date.toString() else "Укажите время!"
            itemView.setOnClickListener {
                infoClickListener?.invoke(item)
            }
            ivDelete.setOnClickListener {
                deleteClickListener?.invoke(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder =
        TodoViewHolder(
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) =
        holder.bind(todoList[position])

    override fun getItemCount(): Int = todoList.size
}



class DiffCallback : DiffUtil.ItemCallback<Todo>() {

    override fun areItemsTheSame(oldItem:Todo, newItem: Todo): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean = areItemsTheSame(oldItem, newItem)

}
