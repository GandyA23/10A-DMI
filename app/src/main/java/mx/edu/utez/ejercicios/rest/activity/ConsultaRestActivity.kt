package mx.edu.utez.ejercicios.rest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import mx.edu.utez.ejercicios.databinding.ActivityConsultaRestBinding
import mx.edu.utez.ejercicios.rest.adapter.AdapterUsuario
import mx.edu.utez.ejercicios.rest.model.Usuario
import mx.edu.utez.ejercicios.rest.viewmodel.ConsultaViewModel
import mx.edu.utez.ejercicios.utils.LoadingScreen

class ConsultaRestActivity : AppCompatActivity(), AdapterUsuario.Eventos {

    lateinit var binding : ActivityConsultaRestBinding
    lateinit var adapter : AdapterUsuario
    lateinit var viewModel : ConsultaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityConsultaRestBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ConsultaViewModel::class.java)
        viewModel.getAll()
        initObservers()
    }

    fun initObservers() {
        viewModel.listado.observe(this) {
            binding.recyclerView.layoutManager = LinearLayoutManager(this@ConsultaRestActivity)
            adapter = AdapterUsuario(this@ConsultaRestActivity, this@ConsultaRestActivity)
            binding.recyclerView.adapter = adapter

            lifecycleScope.launch {
                // Setear los datos y notificar que ya tiene datos
                adapter.submitList(it)

                adapter.notifyDataSetChanged()

                LoadingScreen.hide()
            }
        }

        viewModel.error.observe(this) {
            Toast.makeText(this@ConsultaRestActivity, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.isApiProgress.observe(this) {
            if (it)
                LoadingScreen.show(this@ConsultaRestActivity, "Espere...", false)
            else
                LoadingScreen.hide()
        }
    }

    override fun onItemClick(element: Usuario, position: Int) {
        startActivity(Intent(this, DetailActivityRest::class.java).putExtra("id", element.id.toString()))
    }
}