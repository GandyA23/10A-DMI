package mx.edu.utez.ejercicios.auth

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import mx.edu.utez.ejercicios.databinding.ActivityAuthBinding
import mx.edu.utez.ejercicios.utils.EnvValues
import mx.edu.utez.ejercicios.utils.LoadingScreen

class AuthActivity : AppCompatActivity() {

    lateinit var binding: ActivityAuthBinding

    // Instancia para loguearse con google
    lateinit var google: GoogleSignInOptions
    lateinit var cliente: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAuthBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        google = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(EnvValues.SERVER_ID_GOOGLE)
            .requestEmail()
            .build()

        cliente = GoogleSignIn.getClient(this, google)

        binding.buttonRegister.setOnClickListener {
            LoadingScreen.show(this, "Espere...", false)
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


                LoadingScreen.hide()
            }
        }

        binding.buttonLogin.setOnClickListener {
            LoadingScreen.show(this, "Espere...", false)

            // Inicia sesión con correo y contraseña
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                binding.editTextUser.text.toString(),
                binding.editTextPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this@AuthActivity, "Login correcto: ${it.result.user}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@AuthActivity, "Error al loguearse: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }

                LoadingScreen.hide()
            }
        }

        binding.buttonCheck.setOnClickListener {
            LoadingScreen.show(this, "Espere...", false)

            // Verifica si un usuario se encuentra logueado
            var user = FirebaseAuth.getInstance().currentUser

            if (user != null) {
                Toast.makeText(this, "Usuario: ${user.email}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No hay un usuario logueado", Toast.LENGTH_SHORT).show()
            }

            LoadingScreen.hide()
        }

        binding.buttonLogout.setOnClickListener {
            LoadingScreen.show(this, "Espere...", false)

            // Cierra sesión
            FirebaseAuth.getInstance().signOut()

            // Verifica si un usuario se encuentra logueado
            var user = FirebaseAuth.getInstance().currentUser

            if (user == null) {
                Toast.makeText(this, "Se ha cerrado la sesión", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al cerrar sesión", Toast.LENGTH_SHORT).show()
            }

            LoadingScreen.hide()
        }

        binding.buttonLoginGoogle.setOnClickListener {
            // Cierra sesión
            cliente.signOut()
            cliente.silentSignIn()

            // Lanza la actividad de inicio de sesión
            var intent = cliente.signInIntent

            getResult.launch(intent)
        }
    }

    private val getResult = registerForActivityResult(
        ActivityResultContracts
            .StartActivityForResult()
    ) {
        // Verifica si la sesión de google fue correcta
        if (it.resultCode == Activity.RESULT_OK) {
            var task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            var account = task.getResult(ApiException::class.java)
            var credentials = GoogleAuthProvider.getCredential(account.idToken, null)

            // Una vez iniciado sesión con google, entonces registralo en firebase
            FirebaseAuth.getInstance().signInWithCredential(
                credentials
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this@AuthActivity, "Login correcto: ${it.result.user}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@AuthActivity, "Error al loguearse: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}