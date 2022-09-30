package mx.edu.utez.ejercicios.calculadoramvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import mx.edu.utez.ejercicios.databinding.ActivityMenuFigurasBinding
import kotlin.reflect.KClass

class MenuFigurasActivity : AppCompatActivity() {

    lateinit var binding: ActivityMenuFigurasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuFigurasBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val buttons : Map<Button, KClass<out AppCompatActivity>> = mapOf(
            Pair(binding.buttonCirculo, CirculoActivity::class),
            Pair(binding.buttonCuadrado, CuadradoActivity::class),
            Pair(binding.buttonTriangulo, TrianguloActivity::class),
        )

        buttons.forEach { (button, activity) ->
            button.setOnClickListener {
                startActivity(Intent(this, activity.java))
            }
        }
    }
}