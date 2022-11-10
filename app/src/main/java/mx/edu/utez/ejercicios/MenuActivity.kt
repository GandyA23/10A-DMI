package mx.edu.utez.ejercicios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.messaging.FirebaseMessaging
import mx.edu.utez.ejercicios.auth.AuthActivity
import mx.edu.utez.ejercicios.calculadoramvvm.view.MenuFigurasActivity
import mx.edu.utez.ejercicios.calculatormvp.CalculatorActivity
import mx.edu.utez.ejercicios.databinding.ActivityMenuBinding
import mx.edu.utez.ejercicios.datastore.MenuDatastoreActivity
import mx.edu.utez.ejercicios.db.RegistroActivity
import mx.edu.utez.ejercicios.examenunidad1.MainExamenU1Activity
import mx.edu.utez.ejercicios.listas.ListaActivity
import mx.edu.utez.ejercicios.mapas.MapsActivity
import mx.edu.utez.ejercicios.mvvm.view.MainMvvmActivity
import mx.edu.utez.ejercicios.mvp.MainMvpActivity
import mx.edu.utez.ejercicios.rest.activity.MainRestActivity
import mx.edu.utez.ejercicios.romano.RomanoActivity
import mx.edu.utez.ejercicios.shared.LoginActivity
import mx.edu.utez.ejercicios.splash.SplashActivity
import mx.edu.utez.ejercicios.vistas.MainActivity
import kotlin.reflect.KClass

class MenuActivity : AppCompatActivity() {

    lateinit var binding : ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttons : Map<Button, KClass<out AppCompatActivity>> = mapOf(
            Pair(binding.buttonVistas, MainActivity::class),
            Pair(binding.buttonSplash, SplashActivity::class),
            Pair(binding.buttonListas, ListaActivity::class),
            Pair(binding.buttonDb, RegistroActivity::class),
            Pair(binding.buttonShared, LoginActivity::class),
            Pair(binding.buttonMvp, MainMvpActivity::class),
            Pair(binding.buttonCalculatorMvp, CalculatorActivity::class),
            Pair(binding.buttonExamenUnidadI, MainExamenU1Activity::class),
            Pair(binding.buttonMvvm, MainMvvmActivity::class),
            Pair(binding.buttonFigurasMvvm, MenuFigurasActivity::class),
            Pair(binding.buttonRest, MainRestActivity::class),
            Pair(binding.buttonDatastore, MenuDatastoreActivity::class),
            Pair(binding.buttonAuthFirebase, AuthActivity::class),
            Pair(binding.buttonGoogleMaps, MapsActivity::class),
            Pair(binding.buttonRomano, RomanoActivity::class),
        )

        buttons.forEach { (button, activity) ->
            button.setOnClickListener {
                startActivity(Intent(this, activity.java))
            }
        }

        getFirebaseToken()
    }

    /**
     * En la primera vista siempre es necesario crear un token
     * */
    fun getFirebaseToken () {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (it.isSuccessful) {
                var token = it.result
                Log.d("Notification-Menu -> ", token)
            } else {
                Log.d("Notification-Error -> ", it.exception!!.message!!)
            }
        }
    }

}