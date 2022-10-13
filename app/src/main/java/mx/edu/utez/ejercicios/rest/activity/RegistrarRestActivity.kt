package mx.edu.utez.ejercicios.rest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import mx.edu.utez.ejercicios.databinding.ActivityRegistrarRestBinding
import mx.edu.utez.ejercicios.rest.model.Usuario
import mx.edu.utez.ejercicios.rest.viewmodel.RegistroViewModel
import mx.edu.utez.ejercicios.utils.LoadingScreen

class RegistrarRestActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistrarRestBinding
    lateinit var viewModel : RegistroViewModel
    var genre: String = "male"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegistrarRestBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(RegistroViewModel::class.java)

        // Por default, pon al genero másculino y este será activo
        binding.radioGroupGenre.check(binding.radioButtonMale.id)
        binding.switchStatus.isChecked = true

        binding.buttonRegistrar.setOnClickListener {
            viewModel.store(
                Usuario(
                    null,
                    binding.editTextName.text.toString(),
                    binding.editTextEmail.text.toString(),
                    genre,
                    if(binding.switchStatus.isChecked) "active" else "inactive"
                )
            )

            initObservers()
        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                binding.radioButtonMale.id ->
                    if (checked)
                        genre = "male"

                binding.radioButtonFemale.id ->
                    if (checked)
                        genre = "female"
            }
        }
    }

    fun initObservers() {
        viewModel.data.observe(this) {
            Toast.makeText(this@RegistrarRestActivity, "Se ha registrado el usuario: ${it.id} - ${it.name}", Toast.LENGTH_SHORT).show()
        }

        viewModel.error.observe(this) {
            Toast.makeText(this@RegistrarRestActivity, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.isApiProgress.observe(this) {
            if (it)
                LoadingScreen.show(this@RegistrarRestActivity, "Espere...", false)
            else {
                LoadingScreen.hide()
                startActivity(Intent(this@RegistrarRestActivity, MainRestActivity::class.java))
                finish()
            }
        }
    }
}