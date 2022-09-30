package mx.edu.utez.ejercicios.examenunidad1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mx.edu.utez.ejercicios.databinding.ItemVehiculoBinding
import mx.edu.utez.ejercicios.examenunidad1.entity.VehiculoEntity

class VehiculoAdapter(var context : Context) : ListAdapter<VehiculoEntity, VehiculoAdapter.ViewHolder>(DiffUtilCallback) {

    inner class ViewHolder (private val binding : ItemVehiculoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (element: VehiculoEntity, position: Int) {
            // Realiza el seteo de información
            binding.textViewMarca.text = element.marca
            binding.textViewSubMarca.text = element.subMarca
            binding.textViewAnio.text = element.ano
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var item = ItemVehiculoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    private object DiffUtilCallback : DiffUtil.ItemCallback<VehiculoEntity>() {
        override fun areItemsTheSame(oldItem: VehiculoEntity, newItem: VehiculoEntity): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: VehiculoEntity, newItem: VehiculoEntity): Boolean {
            return true
        }

    }
}