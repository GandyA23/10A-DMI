package mx.edu.utez.ejercicios.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import mx.edu.utez.ejercicios.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var binding : ActivitySplashBinding

    /**
     * El SplashActivity nos ayudará a verificar si el usuario se encuentra logueado en la app
     * Sí esta logueado, entonces lo manda a la pantalla inicial, sino lo manda al login
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launchApp()
    }

    fun launchApp () {
        // Este timer nos ayudará a esperar un tiempo determinado
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeSplashActivity::class.java))
            finish()
        }, 3000)
    }
}