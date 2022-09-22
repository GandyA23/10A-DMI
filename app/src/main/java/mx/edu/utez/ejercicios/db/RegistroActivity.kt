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
                val usuarios : List<EmpleadoEntity> = room.getEmpleadoDao().getAll()
                var strUsuarios = ""

                // Obtiene toda la info de los usuarios
                for(usuario in usuarios)
                    strUsuarios += "\n${usuario.id}.- ${usuario.nombre} - ${usuario.telefono}"

                Toast.makeText(applicationContext, "Usuarios: $strUsuarios \nSize ${room.getEmpleadoDao().getAll().size}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}