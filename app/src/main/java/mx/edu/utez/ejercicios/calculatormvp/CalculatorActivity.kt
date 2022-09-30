package mx.edu.utez.ejercicios.calculatormvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import mx.edu.utez.ejercicios.calculatormvp.interfaces.CalculatorViewInterface
import mx.edu.utez.ejercicios.calculatormvp.presenter.CalculatorPresenter
import mx.edu.utez.ejercicios.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity(), CalculatorViewInterface {

    lateinit var binding: ActivityCalculatorBinding
    lateinit var presenter : CalculatorPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = CalculatorPresenter(this)

        val editTextX : EditText = binding.editTextNumberX
        val editTextY : EditText = binding.editTextNumberY

        binding.buttonSuma.setOnClickListener {
            presenter.suma(
                editTextX.text.toString().toDouble(),
                editTextY.text.toString().toDouble()
            )
        }

        binding.buttonMultiplicacion.setOnClickListener {
            presenter.multiplicacion(
                editTextX.text.toString().toDouble(),
                editTextY.text.toString().toDouble()
            )
        }

        binding.buttonDivision.setOnClickListener {
            presenter.division(
                editTextX.text.toString().toDouble(),
                editTextY.text.toString().toDouble()
            )
        }
    }

    override fun showError(error: String) {
        Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
    }

    override fun showResult(result: Double) {
        binding.editTextNumberResult.setText(result.toString())
    }
}