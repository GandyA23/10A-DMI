package mx.edu.utez.ejercicios.examenunidad1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import mx.edu.utez.ejercicios.EjerciciosApplication.Companion.room
import mx.edu.utez.ejercicios.databinding.ActivityConsultarBinding
import mx.edu.utez.ejercicios.examenunidad1.adapter.VehiculoAdapter

class ConsultarActivity : AppCompatActivity() {

    lateinit var binding: ActivityConsultarBinding
    lateinit var adapter : VehiculoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityConsultarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = VehiculoAdapter(this)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            // Setear los datos y notificar que ya tiene datos
            adapter.submitList(
                room.getVehiculoDao().getAll()
            )
            adapter.notifyDataSetChanged()
        }
    }
}