package mx.edu.utez.ejercicios.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.TextView
import mx.edu.utez.ejercicios.R
import java.lang.Exception

object LoadingScreen {

    var dialog: Dialog? = null

    /**
     * Crea y muestra el Dialog mientras
     * se esta ejecutando una tarea
     **/
    fun show(context: Context?, message: String?, cancelable: Boolean) {
        // Asigna estilos y template al dialog
        dialog = Dialog(context!!)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setContentView(R.layout.dialog)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.setCancelable(cancelable)

        // Asigna el mensaje de espera
        var label = dialog!!.findViewById<TextView>(R.id.textViewLabel)
        label.text = message

        label.width = 400

        // Muestra el dialog
        try {
            dialog!!.show()
        } catch (e : Exception) {
            println(e)
        }
    }

    /**
     * Esconde el dialog
     **/
    fun hide() {
        if (dialog != null)
            try {
                dialog!!.dismiss()
            } catch (e : Exception) {
                println(e)
            }
    }

}