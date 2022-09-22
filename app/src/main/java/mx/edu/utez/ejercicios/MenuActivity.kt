package mx.edu.utez.ejercicios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import mx.edu.utez.ejercicios.databinding.ActivityMenuBinding
import mx.edu.utez.ejercicios.db.RegistroActivity
import mx.edu.utez.ejercicios.listas.ListaActivity
import mx.edu.utez.ejercicios.shared.LoginActivity
import mx.edu.utez.ejercicios.splash.SplashActivity
import mx.edu.utez.ejercicios.vistas.MainActivity
import kotlin.reflect.KClass

class MenuActivity : AppCompatActivity() {

    lateinit var binding : ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttons : Map<Button, KClass<out AppCompatActivity>> = mapOf(
            Pair(binding.buttonVistas, MainActivity::class),
            Pair(binding.buttonSplash, SplashActivity::class),
            Pair(binding.buttonListas, ListaActivity::class),
            Pair(binding.buttonDb, RegistroActivity::class),
            Pair(binding.buttonShared, LoginActivity::class),
        )

        buttons.forEach { (button, activity) ->
            button.setOnClickListener {
                startActivity(Intent(this, activity.java))
            }
        }
    }
}