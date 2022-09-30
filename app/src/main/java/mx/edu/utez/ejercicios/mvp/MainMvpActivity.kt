package mx.edu.utez.ejercicios.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import mx.edu.utez.ejercicios.databinding.ActivityMainMvpBinding
import mx.edu.utez.ejercicios.mvp.presenter.MainPresenter

/** Esta clase equivale al intermediario **/
class MainMvpActivity : AppCompatActivity(), MainInterface {

    lateinit var binding : ActivityMainMvpBinding
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainMvpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter = MainPresenter(this)
        binding.buttonLogin.setOnClickListener {
            // Le dice al presenter que realice la tarea, no es necesario esperar una respuesta porque
            // el presenter la avisará después mediante las funciones implementadas en esta clase (showError y showResult)
            presenter.makeLogin(
                binding.editTextUsername.text.toString(),
                binding.editTextPassword.text.toString()
            )
        }
    }

    // Recibe una respuesta del presenter
    override fun showError(error: String) {
        Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
    }

    override fun showResult(result: String) {
        Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
    }
}