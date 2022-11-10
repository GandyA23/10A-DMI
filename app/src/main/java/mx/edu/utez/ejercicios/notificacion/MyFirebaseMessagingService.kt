package mx.edu.utez.ejercicios.notificacion

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Cuando llega un mensaje, llega a esta clase
 * */
class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        // En caso de que reciba un JSON, entra aquí
        if (message.data.isNotEmpty()) {
            Log.d("Notification-data ->", message.data.toString())
        }

        // Si solo avisa, entonces entra aquí
        message.notification?.let {
            Log.d("Notification-notify ->", message.toString())
        }
    }

}