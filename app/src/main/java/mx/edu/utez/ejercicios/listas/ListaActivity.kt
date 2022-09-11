package mx.edu.utez.ejercicios.listas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import mx.edu.utez.ejercicios.databinding.ActivityListaBinding

class ListaActivity : AppCompatActivity() {

    lateinit var binding: ActivityListaBinding

    /** Mostrar una lista en la vista */
    var data : List<String> = listOf("Gandy", "Violeta", "Brian", "Gustavo", "Dafne", "Rashid")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
    }

    fun setData () {
        // Adapta la información para mostrarla en la vista
        var adapter : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        binding.listView.adapter = adapter
        // Avisa que los datos han sido actualizados
        adapter.notifyDataSetChanged()

        // Obtener un elemento de la lista al momento de darle click
        binding.listView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, data[i], Toast.LENGTH_SHORT).show()
        }
    }
}