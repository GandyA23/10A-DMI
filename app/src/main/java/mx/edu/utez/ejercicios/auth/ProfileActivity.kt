package mx.edu.utez.ejercicios.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import mx.edu.utez.ejercicios.EjerciciosApplication
import mx.edu.utez.ejercicios.databinding.ActivityProfileBinding
import mx.edu.utez.ejercicios.utils.EnvValues

class ProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding

    // Instancia para loguearse con google
    lateinit var google: GoogleSignInOptions
    lateinit var cliente: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        google = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(EnvValues.SERVER_ID_GOOGLE)
            .requestEmail()
            .build()

        cliente = GoogleSignIn.getClient(this, google)

        binding.buttonLogout.setOnClickListener {
            // Cierra sesión
            EjerciciosApplication.sharedDatastore.delete()
            cliente.signOut()
            cliente.silentSignIn()

            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }

        checkLogin()
    }

    /**
     * Verifica si existe un usuario logueado
     * */
    private fun checkLogin() {
        // Verifica si el usuario se encuentra en el shared preference
        val userShared = EjerciciosApplication.sharedDatastore.get()

        if (!userShared["email"].isNullOrEmpty() && !userShared["provider"].isNullOrEmpty()) {
            binding.textViewEmail.text = userShared["email"]
            binding.textViewProvider.text = userShared["provider"]
        } else {
            // En caso de que se encuentre logueado, entonces redirigelo a la vista de profile
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
    }
}