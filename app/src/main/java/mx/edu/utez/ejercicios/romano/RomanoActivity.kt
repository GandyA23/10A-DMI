package mx.edu.utez.ejercicios.romano

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.edu.utez.ejercicios.databinding.ActivityRomanoBinding
import java.lang.Integer.parseInt

class RomanoActivity : AppCompatActivity() {

    lateinit var binding: ActivityRomanoBinding

    private val NUMS_BASE: Map<Int, String> = mapOf(
        1000 to "M",
        900 to "CM", 500 to "D", 400 to "CD", 100 to "C",
        90 to "XC", 50 to "L", 40 to "XL", 10 to "X",
        9 to "IX", 5 to "V", 4 to "IV", 1 to "I"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRomanoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonResultado.setOnClickListener {
            binding.textViewResultado.text = numeroARomano(parseInt(binding.editTextNumero.text.toString()))
        }
    }

    private fun numeroARomano(n: Int): String {
        var romano = ""
        var numAConvertir = n

        NUMS_BASE.forEach{(num, roma) ->
            var can = numAConvertir / num

            while (can-- > 0)
                romano += roma

            numAConvertir %= num
        }

        return romano
    }
}