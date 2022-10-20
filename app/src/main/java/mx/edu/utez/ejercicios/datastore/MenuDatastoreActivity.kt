package mx.edu.utez.ejercicios.datastore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import mx.edu.utez.ejercicios.databinding.ActivityMenuDatastoreBinding
import kotlin.reflect.KClass

class MenuDatastoreActivity : AppCompatActivity() {

    lateinit var binding: ActivityMenuDatastoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuDatastoreBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val buttons : Map<Button, KClass<out AppCompatActivity>> = mapOf(
            Pair(binding.buttonRegistrar, DatastoreActivity::class),
            Pair(binding.buttonConsultar, ConsultaDatastoreActivity::class),
        )

        buttons.forEach { (button, activity) ->
            button.setOnClickListener {
                startActivity(Intent(this, activity.java))
            }
        }
    }
}