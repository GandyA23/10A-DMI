package mx.edu.utez.ejercicios.rest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import mx.edu.utez.ejercicios.MenuActivity
import mx.edu.utez.ejercicios.databinding.ActivityRegistrarRestBinding
import mx.edu.utez.ejercicios.rest.services.ApiService
import mx.edu.utez.ejercicios.utils.EnvValues
import mx.edu.utez.ejercicios.utils.LoadingScreen
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistrarRestActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistrarRestBinding
    var genre: String = "male"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegistrarRestBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Por default, pon al genero másculino y este será activo
        binding.radioGroupGenre.check(binding.radioButtonMale.id)
        binding.switchStatus.isChecked = true

        binding.buttonRegistrar.setOnClickListener {
            LoadingScreen.show(this, "Espere...", false)

            lifecycleScope.launch {
                val call = getRetrofit().create(ApiService::class.java)
                    .create(
                        "${EnvValues.BASE_URL}/users/",
                        Usuario(
                            null,
                            binding.editTextName.text.toString(),
                            binding.editTextEmail.text.toString(),
                            genre,
                            if(binding.switchStatus.isChecked) "active" else "inactive"
                        ),
                        "Bearer ${EnvValues.BEARER_TOKEN}"
                    )

                runOnUiThread {
                    if (call.isSuccessful) {
                        Toast.makeText(applicationContext, "Se ha registrado correctamente el usuario", Toast.LENGTH_SHORT).show()
                        LoadingScreen.hide()
                        startActivity(Intent(applicationContext, MainRestActivity::class.java))
                    } else {
                        val RESPONSE_ERROR = "Ha ocurrido un error al registrar los datos"
                        println(RESPONSE_ERROR)
                        Toast.makeText(applicationContext, RESPONSE_ERROR, Toast.LENGTH_SHORT).show()
                        LoadingScreen.hide()
                    }
                }
            }
        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                binding.radioButtonMale.id ->
                    if (checked)
                        genre = "male"

                binding.radioButtonFemale.id ->
                    if (checked)
                        genre = "female"
            }
        }
    }

    private fun getRetrofit() : Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("${EnvValues.BASE_URL}/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}