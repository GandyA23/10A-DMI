package mx.edu.utez.ejercicios.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.edu.utez.ejercicios.databinding.ActivityHomeSplashBinding

class HomeSplashActivity : AppCompatActivity() {

    lateinit var binding : ActivityHomeSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeSplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}