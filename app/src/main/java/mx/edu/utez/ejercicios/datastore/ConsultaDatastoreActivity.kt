package mx.edu.utez.ejercicios.datastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import mx.edu.utez.ejercicios.databinding.ActivityConsultaDatastoreBinding
import mx.edu.utez.ejercicios.datastore.adapter.UsuarioDatastoreAdapter
import mx.edu.utez.ejercicios.datastore.model.UsuarioDatastore
import mx.edu.utez.ejercicios.utils.LoadingScreen

class ConsultaDatastoreActivity : AppCompatActivity(), UsuarioDatastoreAdapter.Eventos {

    lateinit var binding: ActivityConsultaDatastoreBinding
    lateinit var adapter: UsuarioDatastoreAdapter
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityConsultaDatastoreBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        LoadingScreen.show(this@ConsultaDatastoreActivity, "Espere...", false)
        getAll()
    }

    fun getAll() {
        db.collection("usuarios").get()
            .addOnSuccessListener {
                var response = it.map { elemento ->
                    UsuarioDatastore(
                        elemento.reference.id,
                        elemento["nombre"].toString(),
                        elemento["paterno"].toString(),
                        elemento["materno"].toString(),
                        elemento["edad"].toString().toInt(),
                        elemento["sexo"].toString(),
                    )
                }

                setData(response)
                LoadingScreen.hide()
            }.addOnFailureListener {
                // Muestra un mensaje de error
                println("error -> ${it.message}")
                LoadingScreen.hide()
            }
    }

    fun setData (response: List<UsuarioDatastore>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UsuarioDatastoreAdapter(this, this)
        binding.recyclerView.adapter = adapter
        adapter.submitList(response)
        adapter.notifyDataSetChanged()
    }

    override fun onItemClick(element: UsuarioDatastore, position: Int) {
    }

    override fun onDelete(element: UsuarioDatastore) {
        LoadingScreen.show(this@ConsultaDatastoreActivity, "Eliminando usuario...", false)

        db.collection("usuarios").document(element.id!!).delete()
            .addOnSuccessListener {
                Toast.makeText(
                    this@ConsultaDatastoreActivity,
                    "Se ha eliminado correctamente",
                    Toast.LENGTH_SHORT
                ).show()
                LoadingScreen.hide()
                getAll()
            }.addOnFailureListener {
                // Muestra un mensaje de error
                println("error -> ${it.message}")
                LoadingScreen.hide()
            }
    }
}