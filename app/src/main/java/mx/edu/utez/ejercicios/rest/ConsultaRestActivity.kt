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
import mx.edu.utez.ejercicios.utils.EnvValues
import mx.edu.utez.ejercicios.utils.LoadingScreen
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConsultaRestActivity : AppCompatActivity(), AdapterUsuario.Eventos {

    lateinit var binding : ActivityConsultaRestBinding
    lateinit var adapter : AdapterUsuario

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityConsultaRestBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        LoadingScreen.show(this, "Espere...", false)

        lifecycleScope.launch {
            val call = getRetrofit().create(ApiService::class.java).getAll("${EnvValues.BASE_URL}/users/", "Bearer ${EnvValues.BEARER_TOKEN}")

            runOnUiThread {
               if (call.isSuccessful) {
                   binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                   adapter = AdapterUsuario(this@ConsultaRestActivity, applicationContext)
                   binding.recyclerView.adapter = adapter

                   lifecycleScope.launch {

                       // Setear los datos y notificar que ya tiene datos
                       adapter.submitList(
                           call.body()
                       )

                       adapter.notifyDataSetChanged()

                       LoadingScreen.hide()
                   }
               } else {
                    val RESPONSE_ERROR = "Ha ocurrido un error al consultar los datos"
                    println(RESPONSE_ERROR)
                    Toast.makeText(applicationContext, RESPONSE_ERROR, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MenuActivity::class.java))
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

    override fun onItemClick(element: Usuario, position: Int) {
        startActivity(Intent(this, DetailActivityRest::class.java).putExtra("id", element.id.toString()))
    }
}