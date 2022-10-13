package mx.edu.utez.ejercicios.rest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.edu.utez.ejercicios.databinding.ActivityMainRestBinding

class MainRestActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainRestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainRestBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonConsultar.setOnClickListener {
            startActivity(Intent(this, ConsultaRestActivity::class.java))
        }

        binding.buttonRegistrar.setOnClickListener {
            startActivity(Intent(this, RegistrarRestActivity::class.java))
        }
    }
}