package mx.edu.utez.ejercicios.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import mx.edu.utez.ejercicios.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAuthBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener {
            // Crea un usuario con correo y contraseña
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                binding.editTextUser.text.toString(),
                binding.editTextPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this@AuthActivity, "Registro correcto", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@AuthActivity, "Error al registrar: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}