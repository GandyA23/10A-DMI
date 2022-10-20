package mx.edu.utez.ejercicios.datastore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mx.edu.utez.ejercicios.databinding.ItemDatastoreBinding
import mx.edu.utez.ejercicios.datastore.model.UsuarioDatastore

class UsuarioDatastoreAdapter(private val eventos: Eventos, var context : Context) : ListAdapter<UsuarioDatastore, UsuarioDatastoreAdapter.ViewHolder>(
    DiffUtilCallback
) {

    private val ctx = context

    interface Eventos {
        fun onItemClick(element: UsuarioDatastore, position: Int)
        fun onDelete(element: UsuarioDatastore)
    }

    inner class ViewHolder (private val binding : ItemDatastoreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (element: UsuarioDatastore, position: Int) {
            binding.textViewNombre.text = element.nombre
            binding.textViewPaterno.text = element.paterno
            binding.textViewMaterno.text = element.materno
            binding.textViewGenero.text = element.sexo

            binding.imageViewRemove.setOnClickListener {
                this@UsuarioDatastoreAdapter.eventos.onDelete(element)
            }

            binding.linearLayoutItem1.setOnClickListener {
                this@UsuarioDatastoreAdapter.eventos.onItemClick(element, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var item = ItemDatastoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    private object DiffUtilCallback : DiffUtil.ItemCallback<UsuarioDatastore>() {
        override fun areItemsTheSame(oldItem: UsuarioDatastore, newItem: UsuarioDatastore): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: UsuarioDatastore, newItem: UsuarioDatastore): Boolean {
            return true
        }
    }
}