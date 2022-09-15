package mx.edu.utez.ejercicios.listas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.edu.utez.ejercicios.databinding.Item1Binding

class AdapterAlumno (var context : Context) : ListAdapter<Alumno, AdapterAlumno.ViewHolder> (DiffUtilCallback) {

    private val ctx = context

    // Item1Binding es el archivo res/layout/item_1.xml
    // Interpreda las vistas
    inner class ViewHolder (private val binding : Item1Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (element: Alumno, position: Int) {
            // Realiza el seteo de información
            binding.textViewNombre.text = element.nombre
            Glide.with(ctx).load(element.foto).into(binding.circleImageViewPerfil)

            binding.linearLayoutItem1.setOnClickListener {
                ctx.startActivity(Intent(ctx, AlumnoInfoActivity::class.java).putExtra("alumno", element))
            }
        }
    }

    // Se crea el viewBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var item = Item1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item)
    }

    //
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    /**
     * Nos ayudará a realizar la comparación entre dos objetos
     * */
    private object DiffUtilCallback : DiffUtil.ItemCallback<Alumno>() {
        // Compara por el id
        override fun areItemsTheSame(oldItem: Alumno, newItem: Alumno): Boolean {
            return true
        }

        // Compara todo el contenido del alumno
        override fun areContentsTheSame(oldItem: Alumno, newItem: Alumno): Boolean {
            return true
        }

    }
}