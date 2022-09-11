package mx.edu.utez.ejercicios.vistas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.edu.utez.ejercicios.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // lateinit var buttonEnviar : Button
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        // Traer elementos de la vista (No recomendado)
        buttonEnviar = findViewById(R.id.buttonEnviar)

        buttonEnviar.setOnClickListener {
            Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show()
        }
        */

        /*
        // Traer elementos con Kotlin-Extensions (manda a traer automáticamente)
        // Menos recomendable que findViewById
        buttonEnviar.setOnClickListener {
            Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show()
        }
        */

        // Traer elementos con ViewBinding (Recomendable)
        binding.buttonEnviar.setOnClickListener {
            var nombre = binding.editTextNombre.text.toString()
            var apellidos = binding.editTextApellidos.text.toString()
            var edad = binding.editTextEdad.text.toString()

            var persona = Persona(nombre, apellidos, edad)

            startActivity(Intent(this, ShowDataActivity::class.java)
                .putExtra("persona", persona)
                // Mandarlo por un individual
                // .putExtra("nombre", nombre)
                // .putExtra("apellidos", apellidos)
                // .putExtra("edad", edad)
            )

            // Elimina la instancia de la actividad del formulario
            finish()

            // Toast.makeText(this, "Hola ${nombre} ${apellidos}", Toast.LENGTH_SHORT).show()
        }

    }
}