package mx.edu.utez.ejercicios.calculadoramvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import mx.edu.utez.ejercicios.calculadoramvvm.viewmodel.CirculoViewModel
import mx.edu.utez.ejercicios.databinding.ActivityCirculoBinding

class CirculoActivity : AppCompatActivity() {

    lateinit var binding: ActivityCirculoBinding
    lateinit var viewModel : CirculoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCirculoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(CirculoViewModel::class.java)

        binding.buttonArea.setOnClickListener {
            viewModel.mostrarArea(binding.editTextRadio.text.toString().toDouble())
        }

        binding.buttonPerimetro.setOnClickListener {
            viewModel.mostrarPerimetro(binding.editTextRadio.text.toString().toDouble())
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