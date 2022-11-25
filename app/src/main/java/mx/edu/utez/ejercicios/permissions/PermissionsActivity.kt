package mx.edu.utez.ejercicios.permissions

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import mx.edu.utez.ejercicios.databinding.ActivityPermissionsBinding

class PermissionsActivity : AppCompatActivity() {

    lateinit var binding: ActivityPermissionsBinding

    private val permissions : Array<String> = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.RECORD_AUDIO,
    )

    private val KEY_PERMISSIONS = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPermissionsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnPermissions.setOnClickListener {
            // Verifica si los permisos de la camara ya han sido concedidos
            requestPermissionsDevice()
        }
    }

    private fun checkAllPermissions() {
        var flag = true
        val permissionsNotGranted : MutableList<String> = mutableListOf()

        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsNotGranted.add(permission)
                flag = false
            }
        }

        if (flag) {
            launchApplication()
        }
    }

    private fun launchApplication() {
        Toast.makeText(this, "Todo esta bien :3", Toast.LENGTH_SHORT).show()
    }

    /**
     * Solicita los permisos de cámera al usuario
     * */
    private fun requestPermissionsDevice() {
        ActivityCompat.requestPermissions(
            this,
            permissions,
            KEY_PERMISSIONS
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode) {
            KEY_PERMISSIONS -> {
                var strMessage = ""

                for ((index, result) in grantResults.withIndex()) {
                    if (result != PackageManager.PERMISSION_GRANTED)
                        strMessage += "${this.permissions[index]}\n"
                }

                if (strMessage.isNotBlank()) {
                    showAlert(strMessage)
                } else {
                    launchApplication()
                }
            }
        }
    }

    private fun showAlert(message: String) {
        val dialog = AlertDialog.Builder(this)

        dialog.setMessage("El usuario no ha aceptado los siguientes permisos:\n$message")
            .setCancelable(false)
            .setPositiveButton("OK"){ dialog, id ->

            }

        val alerta = dialog.create()
        alerta.show()
    }
}