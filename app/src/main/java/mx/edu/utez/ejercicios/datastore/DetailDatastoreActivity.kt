package mx.edu.utez.ejercicios.datastore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import mx.edu.utez.ejercicios.databinding.ActivityDetailDatastoreBinding
import mx.edu.utez.ejercicios.datastore.model.UsuarioDatastore
import mx.edu.utez.ejercicios.utils.LoadingScreen

class DetailDatastoreActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailDatastoreBinding
    private val db = FirebaseFirestore.getInstance()
    var genre: String = "Hombre"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailDatastoreBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id : String = intent.getStringExtra("id")!!

        setInitialData(id)

        binding.buttonActualizar.setOnClickListener {
            updateData(id)
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

    fun setInitialData(id: String) {
        LoadingScreen.show(this@DetailDatastoreActivity, "Espere...", false)

        // Consulta la información especifica del usuario
        db.collection("usuarios").document(id).get()
            .addOnSuccessListener {
                var intRadio : Int

                if (it["sexo"] != null && it["sexo"].toString().equals("Mujer")) {
                    intRadio = binding.radioButtonMujer.id
                    genre = it["sexo"].toString()
                } else {
                    intRadio = binding.radioButtonHombre.id
                }

                binding.editTextNombre.setText(if (it["nombre"] != null) it["nombre"].toString() else "")
                binding.editTextPaterno.setText(if (it["paterno"] != null) it["paterno"].toString() else "")
                binding.editTextMaterno.setText(if (it["materno"] != null) it["materno"].toString() else "")
                binding.editTextEdad.setText(if (it["edad"] != null) it["edad"].toString() else "")
                binding.radioGroupSexo.check(intRadio)

                LoadingScreen.hide()
            }.addOnFailureListener {
                // Muestra un mensaje de error
                println("error -> ${it.message}")
                LoadingScreen.hide()
            }
    }

    fun updateData (id: String) {
        LoadingScreen.show(this@DetailDatastoreActivity, "Espere...", false)
        db.collection("usuarios").document(id).update(
            UsuarioDatastore.newElement(
                binding.editTextNombre.text.toString(),
                binding.editTextPaterno.text.toString(),
                binding.editTextMaterno.text.toString(),
                binding.editTextEdad.text.toString().toInt(),
                genre
            )
        ).addOnSuccessListener {
            Toast.makeText(
                this@DetailDatastoreActivity,
                "Se ha actualizado correctamente",
                Toast.LENGTH_SHORT
            ).show()

            LoadingScreen.hide()

            startActivity(Intent(this@DetailDatastoreActivity, ConsultaDatastoreActivity::class.java))
            finish()
        }.addOnFailureListener {
            // Muestra un mensaje de error
            println("error -> ${it.message}")
            LoadingScreen.hide()
        }
    }
}