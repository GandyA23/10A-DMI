package mx.edu.utez.ejercicios.notificacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.edu.utez.ejercicios.databinding.ActivityNotificacionBinding

class NotificacionActivity : AppCompatActivity() {

    lateinit var binding: ActivityNotificacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNotificacionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}