package mx.edu.utez.ejercicios.rest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import mx.edu.utez.ejercicios.databinding.ActivityDetailRestBinding
import mx.edu.utez.ejercicios.rest.ErrorData
import mx.edu.utez.ejercicios.rest.Usuario
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
                    var gson = GsonBuilder().create()
                    var type = object : TypeToken<List<ErrorData>>() {}.type

                    // Se obtienen los errores del json
                    var errores : List<ErrorData> = gson.fromJson(
                        call.errorBody()!!.charStream(),
                        type
                    )

                    val ERROR_MESSAGE = "${errores[0].field}: ${errores[0].message}"
                    
                    println(ERROR_MESSAGE)
                    Toast.makeText(applicationContext, ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
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