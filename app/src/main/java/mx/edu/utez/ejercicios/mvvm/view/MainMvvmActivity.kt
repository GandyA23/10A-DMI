package mx.edu.utez.ejercicios.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import mx.edu.utez.ejercicios.databinding.ActivityMainMvvmBinding
import mx.edu.utez.ejercicios.mvvm.viewmodel.MainViewModel

class MainMvvmActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainMvvmBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainMvvmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Solamente una view puede pertenecer a un provider
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.buttonAcceder.setOnClickListener {
            viewModel.checkLogin(
                binding.editTextUsername.text.toString(),
                binding.editTextPassword.text.toString()
            )
        }

        initObservers()
    }

    // Inicia los observadores para detectar cuando regresen un resultado
    fun initObservers() {
        viewModel.result.observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.error.observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        }
    }
}