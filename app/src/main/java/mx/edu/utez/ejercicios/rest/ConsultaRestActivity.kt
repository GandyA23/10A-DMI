package mx.edu.utez.ejercicios.rest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import mx.edu.utez.ejercicios.MenuActivity
import mx.edu.utez.ejercicios.databinding.ActivityConsultaRestBinding
import mx.edu.utez.ejercicios.rest.services.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConsultaRestActivity : AppCompatActivity() {

    companion object {
        // URL a la cuál realiza peticiones
        const val BASE_URL : String = "https://gorest.co.in/public/v2"
    }

    lateinit var binding : ActivityConsultaRestBinding
    lateinit var adapter : AdapterUsuario

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityConsultaRestBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        lifecycleScope.launch {
            val call = getRetrofit().create(ApiService::class.java).getAll("$BASE_URL/users/")

            runOnUiThread {
               if (call.isSuccessful) {
                   binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                   adapter = AdapterUsuario(applicationContext)
                   binding.recyclerView.adapter = adapter

                   lifecycleScope.launch {

                       // Setear los datos y notificar que ya tiene datos
                       adapter.submitList(
                           call.body()
                       )

                       adapter.notifyDataSetChanged()
                   }
               } else {
                   val RESPONSE_ERROR : String = "Ha ocurrido un error al consultar los datos"
                   println(RESPONSE_ERROR)
                   Toast.makeText(applicationContext, RESPONSE_ERROR, Toast.LENGTH_SHORT).show()
                   startActivity(Intent(applicationContext, MenuActivity::class.java))
                    finish()
               }
            }
        }
    }

    private fun getRetrofit() : Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("$BASE_URL/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}