package mx.edu.utez.ejercicios.rest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import mx.edu.utez.ejercicios.databinding.ActivityDetailRestBinding
import mx.edu.utez.ejercicios.rest.viewmodel.ConsultaViewModel
import mx.edu.utez.ejercicios.utils.LoadingScreen

class DetailActivityRest : AppCompatActivity() {

    lateinit var binding: ActivityDetailRestBinding
    lateinit var viewModel : ConsultaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityDetailRestBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ConsultaViewModel::class.java)
        viewModel.show(intent.getStringExtra("id")!!.toLong())
        initObservers()
    }

    fun initObservers() {
        viewModel.data.observe(this) {
            binding.textViewId.text = it.id.toString()
            binding.textViewName.text = it.name
            binding.textViewEmail.text = it.email
            binding.textViewGenre.text = it.gender
            binding.textViewStatus.text = it.status

            binding.buttonEliminar.setOnClickListener {
                startActivity(Intent(this@DetailActivityRest, EliminarRestActivity::class.java).putExtra("id", intent.getStringExtra("id")!!))
                finish()
            }
        }

        viewModel.error.observe(this) {
            println(it)
            Toast.makeText(this@DetailActivityRest, it, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@DetailActivityRest, ConsultaRestActivity::class.java))
            finish()
        }

        viewModel.isApiProgress.observe(this) {
            if (it)
                LoadingScreen.show(this@DetailActivityRest, "Espere...", false)
            else
                LoadingScreen.hide()
        }
    }
}