package mx.edu.utez.ejercicios.rest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import mx.edu.utez.ejercicios.databinding.ActivityDetailRestBinding
import mx.edu.utez.ejercicios.rest.services.ApiService
import mx.edu.utez.ejercicios.utils.EnvValues
import mx.edu.utez.ejercicios.utils.LoadingScreen
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivityRest : AppCompatActivity() {

    lateinit var binding: ActivityDetailRestBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityDetailRestBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        LoadingScreen.show(this, "Espere...", false)

        lifecycleScope.launch {
            val call = getRetrofit()
                .create(ApiService::class.java)
                .show("${EnvValues.BASE_URL}/users/${intent.getStringExtra("id")}", "Bearer ${EnvValues.BEARER_TOKEN}")

            runOnUiThread {
                if (call.isSuccessful) {
                    var user : Usuario = call.body()!!

                    binding.textViewId.text = user.id.toString()
                    binding.textViewName.text = user.name
                    binding.textViewEmail.text = user.email
                    binding.textViewGenre.text = user.gender
                    binding.textViewStatus.text = user.status

                    LoadingScreen.hide()

                } else {
                    val RESPONSE_ERROR = "Ha ocurrido un error al consultar los datos"
                    println(RESPONSE_ERROR)
                    Toast.makeText(applicationContext, RESPONSE_ERROR, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, ConsultaRestActivity::class.java))
                    finish()

                    LoadingScreen.hide()
                }
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