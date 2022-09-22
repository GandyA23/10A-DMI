package mx.edu.utez.ejercicios.listas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import mx.edu.utez.ejercicios.databinding.ActivityListaBinding

class ListaActivity : AppCompatActivity(), AdapterAlumno.Eventos {

    lateinit var binding: ActivityListaBinding
    lateinit var adapter : AdapterAlumno

    /** Mostrar una lista en la vista */
    var data : List<String> = listOf("Gandy", "Violeta", "Brian", "Gustavo", "Dafne", "Rashid")
    var dataRecycler : MutableList<Alumno> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataRecycler.add(Alumno("Gandy Esaú Ávila Estrada", "https://img.blogs.es/anexom/wp-content/uploads/2021/12/perfil-1024x754.jpg", "Activo", "21"))
        dataRecycler.add(Alumno("Brian Medrano Herrera", "https://img.blogs.es/anexom/wp-content/uploads/2021/12/perfil-1024x754.jpg", "Inactivo", "22"))
        dataRecycler.add(Alumno("Violeta Jazmín Millán Villareal", "https://www.dzoom.org.es/wp-content/uploads/2020/02/portada-foto-perfil-redes-sociales-consejos.jpg", "Inactivo", "22"))
        dataRecycler.add(Alumno("Gustavo Alejandro Lopez Zarate", "https://img.blogs.es/anexom/wp-content/uploads/2021/12/perfil-1024x754.jpg", "Activo", "23"))

        setData()
    }

    fun setData () {
        // Método para RecyclerView
        // Setear el adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AdapterAlumno(this, this) // Realiza el uso de la interfaz y un contexto
        binding.recyclerView.adapter = adapter

        // Setear los datos y notificar que ya tiene datos
        adapter.submitList(dataRecycler)
        adapter.notifyDataSetChanged()

        /*
        // Método para Listas de String
        // Adapta la información para mostrarla en la vista
        // El array adapter solo funciona para String
        var adapter : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        binding.listView.adapter = adapter
        // Avisa que los datos han sido actualizados
        adapter.notifyDataSetChanged()

        // Obtener un elemento de la lista al momento de darle click
        binding.listView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, data[i], Toast.LENGTH_SHORT).show()
        }
        */
    }

    /** interface Eventos **/
    override fun onItemClick(element: Alumno, position: Int) {
        Toast.makeText(this, "Click en ${element.nombre}", Toast.LENGTH_SHORT).show()

        // Es mejor realizar los cambios de interfaz desde un Activity que desde un Adapter
        // Realizar el cambio desde un Adapter puede generar errores
        startActivity(Intent(this, AlumnoInfoActivity::class.java).putExtra("alumno", element))
    }

    override fun onStatusChange(element: Alumno, position: Int, estatus: String) {
        // Cambia el estado del alumno
        dataRecycler[position].estatus = estatus

        // Notifica al adapter que el dato en la posición ${position} ha cambiado
        adapter.notifyItemChanged(position)

        Toast.makeText(this, "Click en ${element.nombre} ${element.estatus}", Toast.LENGTH_SHORT).show()
    }
}