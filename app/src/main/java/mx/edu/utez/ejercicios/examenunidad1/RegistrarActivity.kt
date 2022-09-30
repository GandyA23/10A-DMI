package mx.edu.utez.ejercicios.examenunidad1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import mx.edu.utez.ejercicios.EjerciciosApplication.Companion.room
import mx.edu.utez.ejercicios.databinding.ActivityRegistrarBinding
import mx.edu.utez.ejercicios.examenunidad1.entity.VehiculoEntity

class RegistrarActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonRegistrar.setOnClickListener {
            lifecycleScope.launch{
                    // Verifica el número actual de vehículos
                    room.getVehiculoDao().insert(
                        VehiculoEntity(
                            null,
                            binding.editTextMarca.text.toString(),
                            binding.editTextSubMarca.text.toString(),
                            binding.editTextAnio.text.toString()
                        )
                    )

                    // Muestra el toast y mandalo al menu
                    Toast.makeText(
                        applicationContext,
                        "Se ha guardado satisfactoriamente el vehículo\n",
                        /*
                        "Se ha guardado satisfactoriamente el vehículo\n" +
                            "Marca " + binding.editTextMarca.text.toString() + "\n" +
                            "SubMarca " + binding.editTextSubMarca.text.toString() + "\n" +
                            "Anio " + binding.editTextMarca.text.toString() + "\n",
                            */
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(Intent(applicationContext, MainExamenU1Activity::class.java))
            }
        }
    }
}