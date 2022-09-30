package mx.edu.utez.ejercicios.examenunidad1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import mx.edu.utez.ejercicios.EjerciciosApplication.Companion.room
import mx.edu.utez.ejercicios.databinding.ActivityMainExamenU1Binding

class MainExamenU1Activity : AppCompatActivity() {
    lateinit var binding : ActivityMainExamenU1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainExamenU1Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonConsultar.setOnClickListener {
            lifecycleScope.launch {
                // Verifica si existen datos registrados para mostrarlo
                val vehiculos = room.getVehiculoDao().getAll()

                if (vehiculos.isNotEmpty()) {
                    startActivity(Intent(applicationContext, ConsultarActivity::class.java))
                } else {
                    Toast.makeText(applicationContext, "Aún no se encuentra ningún vehículo registrado", Toast.LENGTH_SHORT).show()
                }
            }

        }

        binding.buttonRegistrar.setOnClickListener {
            startActivity(Intent(this, RegistrarActivity::class.java))
        }
    }
}