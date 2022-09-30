package mx.edu.utez.ejercicios.calculadoramvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import mx.edu.utez.ejercicios.calculadoramvvm.viewmodel.CuadradoViewModel
import mx.edu.utez.ejercicios.databinding.ActivityCuadradoBinding

class CuadradoActivity : AppCompatActivity() {

    lateinit var binding: ActivityCuadradoBinding
    lateinit var viewModel: CuadradoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCuadradoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(CuadradoViewModel::class.java)

        binding.buttonArea.setOnClickListener {
            viewModel.mostrarArea(binding.editTextLado.text.toString().toDouble())
        }

        binding.buttonPerimetro.setOnClickListener {
            viewModel.mostrarPerimetro(binding.editTextLado.text.toString().toDouble())
        }

        initObservers()

    }

    fun initObservers() {
        viewModel.result.observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.error.observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        }
    }
}