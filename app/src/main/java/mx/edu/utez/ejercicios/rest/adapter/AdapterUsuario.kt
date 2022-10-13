package mx.edu.utez.ejercicios.rest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mx.edu.utez.ejercicios.R
import mx.edu.utez.ejercicios.databinding.ItemRestBinding
import mx.edu.utez.ejercicios.rest.model.Usuario

class AdapterUsuario(private val eventos: Eventos, var context : Context) : ListAdapter<Usuario, AdapterUsuario.ViewHolder>(
    DiffUtilCallback
) {

    private val ctx = context

    interface Eventos {
        fun onItemClick(element: Usuario, position: Int)
    }

    inner class ViewHolder (private val binding : ItemRestBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (element: Usuario, position: Int) {
            binding.textViewId.text = element.id.toString()
            binding.textViewNombre.text = element.name
            binding.textViewCorreo.text = element.email
            binding.textViewGenero.text = element.gender
            binding.viewIndicador.setBackgroundColor(
                ContextCompat.getColor(
                    ctx,
                    if (element.status.equals("active"))
                        R.color.verde
                    else
                        R.color.rojo
                )
            )

            binding.linearLayoutItem1.setOnClickListener {
                this@AdapterUsuario.eventos.onItemClick(element, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var item = ItemRestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    private object DiffUtilCallback : DiffUtil.ItemCallback<Usuario>() {
        override fun areItemsTheSame(oldItem: Usuario, newItem: Usuario): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: Usuario, newItem: Usuario): Boolean {
            return true
        }
    }
}