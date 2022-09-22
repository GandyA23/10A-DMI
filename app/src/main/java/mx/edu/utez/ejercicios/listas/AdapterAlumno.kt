package mx.edu.utez.ejercicios.listas

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.edu.utez.ejercicios.R
import mx.edu.utez.ejercicios.databinding.Item1Binding

// Se manda a traer una variable para hacer uso de la interfaz
class AdapterAlumno (private val eventos: Eventos, var context : Context) : ListAdapter<Alumno, AdapterAlumno.ViewHolder> (DiffUtilCallback) {

    private val ctx = context

    interface Eventos {
        fun onItemClick (element: Alumno, position: Int)
        fun onStatusChange (element: Alumno, position: Int, estatus: String)
    }

    // Item1Binding es el archivo res/layout/item_1.xml
    // Interpreda las vistas
    inner class ViewHolder (private val binding : Item1Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (element: Alumno, position: Int) {
            // Realiza el seteo de información
            binding.textViewNombre.text = element.nombre
            Glide.with(ctx).load(element.foto).into(binding.circleImageViewPerfil)

            // Verifica el estatus actual del usuario
            if (element.estatus.isNullOrEmpty()) {
                binding.viewIndicador.setBackgroundColor(ContextCompat.getColor(ctx, R.color.gris))
            }

            // En caso de presionar el botón de check, pondrá la barra lateral de color verde
            binding.imageViewCheck.setOnClickListener {
                binding.viewIndicador.setBackgroundColor(ContextCompat.getColor(ctx, R.color.verde))
                this@AdapterAlumno.eventos.onStatusChange(element, position, "activo")
            }

            // En caso de presionar el botón de remove, pondrá la barra lateral de color rojo
            binding.imageViewRemove.setOnClickListener {
                binding.viewIndicador.setBackgroundColor(ContextCompat.getColor(ctx, R.color.rojo))
                this@AdapterAlumno.eventos.onStatusChange(element, position, "in-activo")
            }

            // AL momento de darle click a la tarjeta, muestra la info en otro Activity
            binding.linearLayoutItem1.setOnClickListener {
                this@AdapterAlumno.eventos.onItemClick(element, position)
                // No es bueno empezar actividades desde el Adapter, es por eso que se aplica una interfaz
                // ctx.startActivity(Intent(ctx, AlumnoInfoActivity::class.java).putExtra("alumno", element))
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