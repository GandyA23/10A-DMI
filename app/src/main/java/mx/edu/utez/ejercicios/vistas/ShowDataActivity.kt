package mx.edu.utez.ejercicios.vistas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.edu.utez.ejercicios.databinding.ActivityShowDataBinding

class ShowDataActivity : AppCompatActivity() {

    lateinit var binding : ActivityShowDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // De forma individual
        // var nombre = intent.getStringExtra("nombre")
        // var apellidos = intent.getStringExtra("apellidos")
        // var edad = intent.getStringExtra("edad")

        // binding.textViewNombre.text = persona.nombre
        // binding.textViewApellidos.text = persona.apellidos
        // binding.textViewEdad.text = "${persona.edad} años"

        // Con uso de objetos
        val persona = intent.getSerializableExtra("persona") as Persona
        persona.edad = "${persona.edad} años"
        binding.personaXml = persona

        binding.buttonRegresar.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}