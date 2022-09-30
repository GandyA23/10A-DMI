package mx.edu.utez.ejercicios.shared

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import mx.edu.utez.ejercicios.EjerciciosApplication
import mx.edu.utez.ejercicios.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonGuardar.setOnClickListener {
            EjerciciosApplication.shared.save(
                binding.editTextUsername.text.toString(),
                binding.editTextPassword.text.toString()
            )
        }

        binding.buttonConsultar.setOnClickListener {
            val usuario : String = EjerciciosApplication.shared.get()

            Toast.makeText(
                applicationContext,
                if (usuario.isNullOrEmpty()) "No hay ningún usuario logueado" else "El usuario es $usuario",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}