package mx.edu.utez.ejercicios.datastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import mx.edu.utez.ejercicios.databinding.ActivityDatastoreBinding
import mx.edu.utez.ejercicios.datastore.model.UsuarioDatastore

class DatastoreActivity : AppCompatActivity() {

    lateinit var binding: ActivityDatastoreBinding
    private val db = FirebaseFirestore.getInstance()
    var genre: String = "Hombre"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDatastoreBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.radioGroupSexo.check(binding.radioButtonHombre.id)

        binding.buttonRegistrar.setOnClickListener {
            // Agrega al usuario a la DB de Firebase
            db.collection("usuarios").add(
                UsuarioDatastore(
                    binding.editTextNombre.text.toString(),
                    binding.editTextPaterno.text.toString(),
                    binding.editTextMaterno.text.toString(),
                    binding.editTextEdad.text.toString().toInt(),
                    genre
                )
            ).addOnSuccessListener {
                // Muestra el id
                Toast.makeText(applicationContext, "id ${it.id}", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                // Muestra un mensaje de error
                println("error -> ${it.message}")
            }
        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                binding.radioButtonHombre.id ->
                    if (checked)
                        genre = "Hombre"

                binding.radioButtonMujer.id ->
                    if (checked)
                        genre = "Mujer"
            }
        }
    }
}