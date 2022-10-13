package mx.edu.utez.ejercicios.rest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import mx.edu.utez.ejercicios.rest.viewmodel.EliminarViewModel
import mx.edu.utez.ejercicios.utils.LoadingScreen

class EliminarRestActivity : AppCompatActivity() {

    lateinit var viewModel : EliminarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EliminarViewModel::class.java)
        viewModel.delete(intent.getStringExtra("id")!!.toLong())
        initObservers()
    }

    fun initObservers() {
        viewModel.success.observe(this) {
            Toast.makeText(this@EliminarRestActivity, it, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@EliminarRestActivity, ConsultaRestActivity::class.java))
            finish()
        }

        viewModel.error.observe(this) {
            Toast.makeText(this@EliminarRestActivity, it, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@EliminarRestActivity, DetailActivityRest::class.java).putExtra("id", intent.getStringExtra("id")))
            finish()
        }

        viewModel.isApiProgress.observe(this) {
            if (it)
                LoadingScreen.show(this@EliminarRestActivity, "Espere...", false)
            else
                LoadingScreen.hide()
        }
    }
}