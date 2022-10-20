package mx.edu.utez.ejercicios.datastore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mx.edu.utez.ejercicios.databinding.ItemDatastoreBinding
import mx.edu.utez.ejercicios.datastore.model.UsuarioFullDatastore

class UsuarioDatastoreAdapter(private val eventos: Eventos, var context : Context) : ListAdapter<UsuarioFullDatastore, UsuarioDatastoreAdapter.ViewHolder>(
    DiffUtilCallback
) {

    private val ctx = context

    interface Eventos {
        fun onItemClick(element: UsuarioFullDatastore, position: Int)
        fun onDelete(element: UsuarioFullDatastore)
    }

    inner class ViewHolder (private val binding : ItemDatastoreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (element: UsuarioFullDatastore, position: Int) {
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

    private object DiffUtilCallback : DiffUtil.ItemCallback<UsuarioFullDatastore>() {
        override fun areItemsTheSame(oldItem: UsuarioFullDatastore, newItem: UsuarioFullDatastore): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: UsuarioFullDatastore, newItem: UsuarioFullDatastore): Boolean {
            return true
        }
    }
}