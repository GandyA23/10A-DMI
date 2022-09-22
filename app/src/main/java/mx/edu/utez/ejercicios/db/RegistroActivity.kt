package mx.edu.utez.ejercicios.db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import mx.edu.utez.ejercicios.EjerciciosApplication.Companion.room
import mx.edu.utez.ejercicios.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {

    lateinit var binding : ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonGuardar.setOnClickListener {
            // Realiza la co-rutina para insertar
            lifecycleScope.launch {
                room.getEmpleadoDao().insert(
                    EmpleadoEntity(
                        null,
                        binding.editTextNombre.text.toString(),
                        binding.editTextTelefono.text.toString()
                    )
                )
            }
        }

        binding.buttonConsultar.setOnClickListener {
            // Realiza la co-rutina para consulta
            lifecycleScope.launch {
                Toast.makeText(applicationContext, "Size ${room.getEmpleadoDao().getAll().size}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}